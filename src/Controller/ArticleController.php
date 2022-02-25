<?php

namespace App\Controller;

use App\Entity\Article;
use App\Entity\Commentaire;
use App\Form\ArticleType;
use App\Form\CommentaireType;
use App\Repository\ArticleRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ArticleController extends AbstractController
{
    /**
     * @Route("/article", name="article")
     */
    public function index(): Response
    {
        return $this->render('article/index.html.twig', [
            'controller_name' => 'ArticleController',
        ]);
    }

    /**
     * @param Request $request
     * @return Response
     * @Route("article/Add", name="addArticle")
     * @throws \Exception
     */
    function Add(Request $request): Response
    {
        $article = new Article();
//        $article->setCreatedAt(new \DateTimeImmutable());
        $form = $this->createForm(ArticleType::class, $article);
        $form->add('Ajouter', SubmitType::class);
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
    'commentaire' => $comment,

]);
}
    /**
     * @Route("/articlesList/show",name="articleslist")
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
    public function showfront(ArticleRepository $repo)
    {
        $repo=$this->getDoctrine()->getRepository(Article::class);
        $article = $repo->findAll();
        return $this->render('Front/blog.html.twig', [
            'article' => $article
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
        $form->add('modifier',SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted()&&$form->isValid()) {
            $file=$form->get('media')->getData();
            //$filename=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('images_directory'),
                $filename);
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
        $article=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($article);
        $em->flush();
        return $this->redirectToRoute('articleslist');
    }
//    /**
//     * @Route("/commentaire/new", name="add_comentaire")
//     */
//    public function ajaxAction(Request $request)
//    {
//        $commentaires= $this->getDoctrine()->getRepository(Commentaire::class)->listCommentaireByArticle($id);
//        if ($request->isXMLHttpRequest()) {
//            return new JsonResponse(array('data' => 'this is a json response'));
//        }
//
//        return new Response('This is not ajax!', 400);
//    }
}
