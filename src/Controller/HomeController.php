<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class HomeController extends AbstractController
{
    /**
     * @Route("/test", name="test")
     */
    public function index(): Response
    {
        return $this->render('back-office.html.twig', [
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
     * @Route("/single-blog", name="singleblog")
     */
    public function singleblog(): Response
    {
        return $this->render('/front/blog-details.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }
}
