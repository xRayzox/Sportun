<?php

namespace App\Controller;

use App\Entity\Article;
use App\Entity\Commentaire;
use App\Form\ArticleType;
use App\Form\CommentaireType;
use App\Repository\ArticleRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\UX\Chartjs\Builder\ChartBuilderInterface;

class ArticleController extends AbstractController
{
    /**
     * @Route("/article", name="article")
     */
    public function index(): Response
    {
//        $user=$this->getUser()->getUsername();
        return $this->render('article/index.html.twig', [
            'controller_name' => 'ArticleController',
//            'user'=>$user
        ]);
    }

    /**
     * @param Request $request
     * @return Response
     * @Route("articleAdd", name="addArticle")
     * @throws \Exception
     */
    function Add(Request $request): Response
    {
        $article = new Article();
        $form = $this->createForm(ArticleType::class, $article);
        $form->add('Ajouter', SubmitType::class,['attr'=>['class'=>'class="col-sm-2 col-form-label']]);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $file=$form->get('media')->getData();
            $filename=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('images_directory'),
            $filename);
            $em = $this->getDoctrine()->getManager();
            $article->setMedia($filename)
            ->setCreatedAt(new \DateTimeImmutable());
            $em->persist($article);
            $em->flush();
            return $this->redirectToRoute('articleslist');
        }
        return $this->render('Back/addarticle.html.twig', [
            'form' => $form->createView()
        ]);
    }
    /**
     * @Route("/singleblogback/{id}", name="showArticleBack")
     */
    public function showArticleBack($id): Response
    {
        $post= $this->getDoctrine()->getRepository(Article::class)->find($id);
        $comment= $this->getDoctrine()->getRepository(Commentaire::class)->listCommentaireByArticle($post->getId());
return $this->render('Back/showarticle.html.twig', [
    'article' => $post,
    'commentaires' => $comment,
]);
}
    /**
     * @Route("/articlesList",name="articleslist")
     */
    public function show(ArticleRepository $repo)
    {
        // $repo=$this->getDoctrine()->getRepository(Article::class);
        $article = $repo->findAll();
        return $this->render('Back/blog.html.twig', [
            'article' => $article
        ]);
    }
    /**
     * @Route("/blog",name="blog")
     */
    public function showfront(ArticleRepository $repo,Request $request,PaginatorInterface $paginator)
    {
        $repo=$this->getDoctrine()->getRepository(Article::class);
        $article = $repo->findAll();

        $articles=$paginator->paginate(
            $article,
            $request->query->getInt('page',1),4
        );
        return $this->render('Front/blog.html.twig', [
//            'article' => $article,
            'article'=>$articles
        ]);
    }

    /**
     * @Route("/updateArticle/{id}", name="updateArticle")
     */
    public function updateArticle(Request $request,$id)
    {
        $article = $this->getDoctrine()->getRepository(Article::class)->find($id);
        $filename=$article->getmedia();
        $form = $this->createForm(ArticleType::class, $article);
        $form->add('modifier',SubmitType::class,['attr'=>['class'=>'class="col-sm-2 col-form-label']]);
        $form->handleRequest($request);
        if ($form->isSubmitted()&&$form->isValid()) {
            $fil=$form->get('media')->getData();
            if ($fil!=null) {
                $fil->move($this->getParameter('images_directory'),
                    $filename);
            }
            $em = $this->getDoctrine()->getManager();
            $article->setMedia($filename);
            $em->persist($article);
            $em->flush();
            return $this->redirectToRoute('articleslist');
        }
        return $this->render("Back/updatearticle.html.twig",array('form'=>$form->createView()));
    }
    /**
     * @Route ("/SupprimerArticle/{id}",name="supprimerArticle")
     */
    function Delete($id,ArticleRepository $repository){
        $article = $this->getDoctrine()->getRepository(Article::class)->find($id);
        $filename=$article->getMedia();
        unlink($this->getParameter('images_directory').'/'.$filename);
        $em=$this->getDoctrine()->getManager();
        $em->remove($article);
        $em->flush();
        return $this->redirectToRoute('articleslist');
    }
    public function searchAction(Request $request,NormalizerInterface $Normalizer): Response
    {
        $repository = $this->getDoctrine()->getRepository(Article::class);
        $requestString=$request->get('searchValue');
        $articles = $repository->searchArticle($requestString);
        $jsonContent = $Normalizer->normalize($articles, 'json',['groups'=>'articles:read']);
        $retour=json_encode($jsonContent);
        return new Response($retour);
    }
    public function backChart(ChartBuilderInterface $chartBuilder){
        $chart = $chartBuilder->createChart(Chart::TYPE_LINE);

        $chart->setData([
            'labels' => ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            'datasets' => [
                [
                    'label' => 'My First dataset',
                    'backgroundColor' => 'rgb(255, 99, 132)',
                    'borderColor' => 'rgb(255, 99, 132)',
                    'data' => [0, 10, 5, 2, 20, 30, 45],
                ],
            ],
        ]);

        $chart->setOptions([
            'scales' => [
                'y' => [
                    'suggestedMin' => 0,
                    'suggestedMax' => 100,
                ],
            ],
        ]);

        return $this->render('Back/blog.html.twig', [
            'chart' => $chart,
        ]);
    }
}
