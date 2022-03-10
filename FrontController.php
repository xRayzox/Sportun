<?php

namespace App\Controller;
use App\Form\CategorieType;
use App\Entity\Categorie;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use App\Repository\CategorieRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
class FrontController extends AbstractController
{
  
     /**
     * @Route("/front", name="categoriee", methods={"GET"})
     */
    public function index(CategorieRepository $CategorieRepository): Response
    {
        return $this->render('Front/index.html.twig', [
            'Categories' => $CategorieRepository->findAll(),
        ]);
    }
}