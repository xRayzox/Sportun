<?php

namespace App\Controller;

use App\Entity\Article;
use App\Entity\Commentaire;
use App\Entity\PostLike;
use App\Form\CommentaireType;
use App\Repository\ArticleRepository;
use App\Repository\CommentaireRepository;
use App\Repository\PostLikeRepository;
use Doctrine\ORM\EntityManagerInterface;
use phpDocumentor\Reflection\Types\This;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class CommentaireController extends AbstractController
{
    /**
     * @Route("/commentaire", name="commentaire")
     */
    public function index(): Response
    {
        return $this->render('commentaire/index.html.twig', [
            'controller_name' => 'CommentaireController',
        ]);
    }
    /**
     * @Route("/single-blog/{id}", name="showArticle")
     */
    public function showArticle( $id, Request $request , Article $post ): Response
    {
        $comment = new Commentaire();
        $form = $this->createForm(CommentaireType::class, $comment);
        $form->add('Ajouter', SubmitType::class,
            ['attr'=>['class'=>'btn'],
                ]);
        $form->handleRequest($request);
//        if ($form->isSubmitted() && $form->isValid())
//        {
//
//            $comment->setArticle($post);
//       //     ->setCreatedAt(new \ DateTimeImmutable())
//            $entityManager = $this->getDoctrine()->getManager();
//            $entityManager->persist($comment);
//            $entityManager->flush();
//        }
        $post= $this->getDoctrine()->getRepository(Article::class)->find($id);

        $comment= $this->getDoctrine()->getRepository(Commentaire::class)->listCommentaireByArticle($post->getId());

        return $this->render('Front/blog-details.html.twig', [
            'article' => $post,
            'commentaires' => $comment,
            'form' => $form->createView()
        ]);
    }

    /**
     * @Route("/suppcomnt/{id}", name="commentaire_delete")
     */
    public function delete($id, CommentaireRepository $repository): Response
    {
        $commentaire=$repository->find($id);
        $post = $commentaire->getArticle();
        $em=$this->getDoctrine()->getManager();
        $em->remove($commentaire);
        $em->flush();
        return $this->redirectToRoute('showArticle',['id'=>$post->getId()]);
    }
    /**
     * @Route("/suppback/{id}", name="commentaire_deleteback")
     */
    public function deleteback($id, CommentaireRepository $repository): Response
    {
        $commentaire=$repository->find($id);
        $post = $commentaire->getArticle();
        $em=$this->getDoctrine()->getManager();
        $em->remove($commentaire);
        $em->flush();
        return $this->redirectToRoute('commentes',['id'=>$post->getId()]);
    }
    /**
     * @Route("/commentsList",name="commentes")
     */
    public function show(CommentaireRepository $repo)
    {
        // $repo=$this->getDoctrine()->getRepository(Article::class);
        $com = $repo->findAll();
        return $this->render('Back/comments.html.twig', [
            'commentaires' => $com
        ]);
    }
    /**
     * @Route("/updatecomment/{id}", name="updateCommentaire")
     */
    public function updateCommentaire(Request $request, $id,CommentaireRepository $rep)
    {
        $comment = $rep->find($id);
        $post = $comment->getArticle();
        $form = $this->createForm(CommentaireType::class, $comment);
        $form->add("Modifier", SubmitType::class,['attr'=>['class'=>'btn'],
        ]);
        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isValid()) {
//            ->setCreatedAt(new \ DateTimeImmutable())
            $comment->setArticle($post);
            $em = $this->getDoctrine()->getManager();
            $em->persist($comment);
            $em->flush();
            return $this->redirectToRoute('showArticle',['id'=>$post->getId()]);
        }
        return $this->render("Front/modifoercomment.html.twig", array('form' => $form->createView()));
    }
    /**
     * @Route("/commentaire/{idAct}", name="commentaire")
     */
    public function getcomment($idAct): Response
    {
        $article = $this->getDoctrine()->getRepository(Article::class)->find($idAct);
        $commentaires =$this->getDoctrine()->getRepository(Commentaire::class)->listCommentaireByArticle($article->getId());
        $dt = array();
        foreach ($commentaires as $key => $cat) {
            $dt[$key]['id'] = $cat->getId();
//            $dt[$key]['username'] = $cat->getIduser()->getNom();
            $dt[$key]['username'] = $cat->getUsername();
            $dt[$key]['text'] = $cat->getText();
//            $dt[$key]['dateCom'] = $cat->getDateCom();
            // $dt[$key]['images'] = $cat->getDureExercice();
        }
        return new JsonResponse($dt);
    }
    /**
     * @Route("/commentaire/add/{msg}/{idArt}", name="commentaireadd")
     */
    public function setcomment($idArt, $msg): Response
    {
        //$user=$this->getUser();
        //$user = $this->getDoctrine()->getRepository(User::class)->find();
        $user="aaaa";
        $cm = new Commentaire();
        $cm->setUsername($user);
        $dte = date('Y-m-d h:m');
        var_dump($dte);
        $article = $this->getDoctrine()->getRepository(Article::class)->find($idArt);
        $cm->setArticle($article);
        $cm->setText($msg);
        //$cm->setDateCom($dte);
        $em = $this->getDoctrine()->getManager();
        $em->persist($cm);
        $em->flush();
        return new JsonResponse($cm);
    }

//    /**
//     *
//     * @Route ("/publication/{id}/like", name="PosteLike")
//     * @param Article $article
//     * @param EntityManagerInterface $manager
//     * @param PostLikeRepository $likeRepository
//     * @param
//     * @return Response
//     */
//    public function like(Article $article,PostLikeRepository $likeRepository,EntityManagerInterface $manager,$id):Response
//    {
//
//        // $user = $this->getUser();
//
//        $idu=44; //id ta3 utilisateur
//        $user = $this->getDoctrine()->getRepository(User::class)->find($idu);
//        //   $publications=$this->getDoctrine()->getRepository(Publications::class)->find($id);
//
//
//        if (!$user) return $this->json([
//            'code' => 403,
//            'message' => "Unauthorized"
//        ], 403);
//
//
//
//        if($article->JaimePar($user)) {
//            $like = $likeRepository->findOneBy ([
//                'post' => $article,
//                'user' => $user
//
//            ]);
//            $manager->remove($like);
//            $manager->flush();
//
//            return $this->json([
//                'code' => 200,
//                'message' => 'Like bien supprimé',
//
//                'likes' => $likeRepository->count(['post' => $article])
//            ], 200);
//        }
//
//        $like= new  PostLike();
//        $like->setArticle($article)
//            ->setUser($user);
//
//        $manager->persist($like);
//        $manager->flush();
//
//        return $this->json([
//            'code' => 200,
//            'message' => 'Like bien ajouté',
//            'likes' => $likeRepository->count(['post' => $article])
//        ], 200);
//    }
}
