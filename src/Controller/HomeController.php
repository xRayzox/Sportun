<?php

namespace App\Controller;
use  App\Entity\Map;
use  App\Entity\Location;
use App\Entity\Service;
use App\Form\MapType;
use App\Form\ServiceType;
use App\Form\LocationType;
use App\Repository\ServiceRepository;
use Doctrine\ORM\Mapping\Entity;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


class HomeController extends AbstractController
{
    /**
     * @Route("/test", name="test")
     */
    public function index(): Response
    {
        return $this->render('base.html.twig', [
            'controller_name' => 'HomeController',
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
     * @Route("/map", name="map")
     */
    public function map(Request $request): Response
    {
        $map = $this->getDoctrine()->getRepository(Service::class)->locall();
          /*$output = array();
    foreach($map as $map)
        {
            $output[] = array($map->getName(), $map->getLat(),$map->getLng(),$map->getContent());
        }
        //return new JsonResponse($output);
       
        return $this->render('/front/map.html.twig', 
        array('cord' => $map,
        'json_map' => json_encode($output)),
    ); */
    if ($request->isXmlHttpRequest() || $request->query->get('showJson') == 1) {  
        $jsonData = array();  
        $idx = 0;  
        foreach($map as $student) {  
           $temp = array(
            'name' => $student->getName(),  
            'Description' => $student->getDescription(), 
            'Title' => $student->getTitle(),
            'Type' => $student->getType(),
            'Num_tel' => $student->getNumTel(),  
            'Image' => $student->getImage(), 
            'lat' => $student->getLocation()->getLat(),
            'lng' => $student->getLocation()->getLng(),
            'region' => $student->getLocation()->getregion(),
           );   
           $jsonData[$idx++] = $temp;  
        } 
        array('json_map'=> $jsonData);
        return new JsonResponse($jsonData); 
        return $this->render('Front/map.html.twig',array('json_map'=> $jsonData)); 
     } else { 
        return $this->render('Front/map.html.twig'); 
     } 
  
    }

           /**
     * @Route("/mapadd", name="mapadd")
     */
    public function mapadd(Request $request): Response
    {
        
        $map = new Service();
        $form = $this->createForm(ServiceType::class, $map);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($map);
            $em->flush();
        }
        return $this->render("test_map_crud.html.twig",array('form'=>$form->createView()));
    }
   /**
     * @Route("/tests", name="tests")
     */
    public function test()
    { 
        $Service = $this->getDoctrine()->getRepository(Service::class)->locall();
        
        return $this->render('test_aff.html.twig',array("service" => $Service));
    }

}