<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\UserRepository;
use App\Entity\User;
use App\Form\RegistrationFormType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\FileType;

use Symfony\Component\HttpFoundation\Request;









class HomeController extends AbstractController
{
    /**
     * @Route("/test", name="test")
     */
    public function index(UserRepository $UserRepository): Response
    {
        return $this->render('Back\utilisateur.html.twig', [
            'users' =>$UserRepository->findAll(),
        ]);
    }
    /**
     * @Route("/blog", name="blog")
     */
    public function blog(): Response
    {
        return $this->render('/front/blog.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }
       /**
     * @Route("/about", name="about")
     */
    public function about(): Response
    {
        return $this->render('/front/about.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }
        /**
     * @Route("/home", name="home")
     */
    public function home(): Response
    {
        return $this->render('/front/home.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }
        /**
     * @Route("/produit", name="produit")
     */
    public function produit(): Response
    {
        return $this->render('/front/produit.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }
          /**
     * @Route("/event", name="service")
     */
    public function service(): Response
    {
        return $this->render('/front/event.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }
        /**
     * @Route("/sign_in", name="service")
     */
    public function sign_in(): Response
    {
        return $this->render('/front/sign in.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }
          /**
     * @Route("/registre", name="registre")
     */
    public function registre(): Response
    {
        return $this->render('/front/registre.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }




    /**
     * @Route("/user/list", name="user_show")
     */
    public function show(UserRepository $UserRepository): Response
    {
        return $this->render('Back/utilisateur.html.twig', [
            'users' => $UserRepository->findAll(),
        ]);
    }

/**
 * @Route("/{id}/eddit", name="updateuser")
 */
public function updateuser(Request $request,$id)
{
    $user = $this->getDoctrine()->getRepository(User::class)->find($id);
    $form = $this->createForm(RegistrationFormType::class, $user);
    $form->add('modifier',SubmitType::class);
    if ($form->isSubmitted()) {
        $em = $this->getDoctrine()->getManager();
        $em->flush();
        return $this->redirectToRoute('User');
    }
    return $this->render("Back/Edit.html.twig",array('form'=>$form->createView()));
}

            /**
             * @Route("/deleteuser/{id}", name="deleteuser")
             */
    public function deleteuser($id)
        {
            $user = $this->getDoctrine()->getRepository(User::class)->find($id);
            $em = $this->getDoctrine()->getManager();
            $em->remove($user);
            $em->flush();
            return $this->redirectToRoute('user_show');
        }}

