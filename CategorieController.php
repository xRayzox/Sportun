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
class CategorieController extends AbstractController
{
  
     /**
     * @Route("/categorie", name="categoriees", methods={"GET"})
     */
    public function index(CategorieRepository $CategorieRepository): Response
    {
        return $this->render('categorie/Affiche.html.twig', [
            'Categories' => $CategorieRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="Categorie_new")
     */
    public function new(Request $request): Response
    {
        $Categorie = new Categorie();
        $form = $this->createForm(CategorieType::class, $Categorie);
        $form->add('Ajouter', SubmitType::class);
        $form->handleRequest($request);//traiter la requette recu
        
        if ($form->isSubmitted() && $form->isValid()) {//isvalid pour control de saisie(les entités)
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($Categorie);//persist=insert into
            $entityManager->flush();

            return $this->redirectToRoute('categoriee');
        }

        return $this->render('categorie/index.html.twig', array
           ( 'Categorie' => $Categorie,
            'form' => $form->createView(),
        ));
    }

    /**
     * @Route("/{id}", name="Categorie_show", )
     **/
   /* public function show(Categorie $Categorie): Response
    {
        return $this->render('Categorie/Affiche.html.twig', [
           'Categorie' => $Categorie,
        ]);
    }*/
 
  
   /**
     * @Route("/{id}/edit", name="updatecategorie")
     */
    public function updatecategorie(Request $request,$id)
    {
        $categorie = $this->getDoctrine()->getRepository(Categorie::class)->find($id);
        $form = $this->createForm(CategorieType::class, $categorie);
        $form->add('modifier',SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('Categorie_show');
        }
        return $this->render("categorie/Edit.html.twig",array('form'=>$form->createView()));
    }

    /**
     * @Route("/deleteCategorie/{id}", name="deleteCategorie")
     */
    public function deletecategorie($id)
    {
        $categorie = $this->getDoctrine()->getRepository(Categorie::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($categorie);
        $em->flush();
        return $this->redirectToRoute("categorie");
    }
}
