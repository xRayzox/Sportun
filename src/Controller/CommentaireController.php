<?php

namespace App\Controller;

use App\Entity\Article;
use App\Entity\Commentaire;
use App\Form\CommentaireType;
use App\Repository\ArticleRepository;
use App\Repository\CommentaireRepository;
use phpDocumentor\Reflection\Types\This;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

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
        if ($form->isSubmitted() && $form->isValid())
        {

            $comment->setArticle($post);
       //     ->setCreatedAt(new \ DateTimeImmutable())
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($comment);
            $entityManager->flush();
        }
        $post= $this->getDoctrine()->getRepository(Article::class)->find($id);

        $comment= $this->getDoctrine()->getRepository(Commentaire::class)->listCommentaireByArticle($post->getId());

        return $this->render('Front/blog-details.html.twig', [
            'article' => $post,
            'commentaires' => $comment,
            'form' => $form->createView()

        ]);
    }

    /**
     * @Route("/supp/{id}", name="commentaire_delete")
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
     * @Route("/commentaire/new", name="add_comentaire")
     */
    public function ajaxAction(Request $request)
    {
       // $commentaires= $this->getDoctrine()->getRepository(Commentaire::class)->listCommentaireByArticle($id);
        if ($request->isXMLHttpRequest()) {
            return new JsonResponse(array('data' => 'this is a json response'));
        }

        return new Response('This is not ajax!', 400);
    }
}
