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

use Symfony\Component\Serializer\Normalizer\NormalizerInterface;    
use Dompdf\Dompdf;
use Dompdf\Options;


class HomeController extends AbstractController
{
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
        
        $map = new map();
        $form = $this->createForm(mapType::class, $map);
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
/////////back///////////////

/**
     * @Route("/AddServ", name="AddServ")
     */
public function backk(Request $request): Response
{
    $map = new Service();
        $formm = $this->createForm(ServiceType::class, $map);
        $formm->handleRequest($request);
      
        if (($formm->isSubmitted())&&($formm->isValid())) {
            $file=$formm->get('Image')->getData();
           $filename=md5(uniqid()).'.'.$file->guessExtension();
           $file->move($this->getParameter('images_directory'),$filename);
            $em = $this->getDoctrine()->getManager();
           $map->setImage($filename);
            $em->persist($map);
            $em->flush();
            return $this->redirectToRoute('listService');
        }
        return $this->render("Back/map.html.twig",array('formm'=>$formm->createView()));
}
/**
     * @Route("/listService", name="listService")
     */
    public function listService()
    {
        $services = $this->getDoctrine()->getRepository(Service::class)->locall();
        return $this->render('Back/affichee.html.twig', array("Services" => $services));
    }
 /**
     * @Route("/deleteService/{id}", name="deleteService")
     */
    public function deleteClassroom($id)
    {
        $Service = $this->getDoctrine()->getRepository(Service::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($Service);
        $em->flush();
        return $this->redirectToRoute("listService");
    }
/**
     * @Route("/updateService/{id}", name="updateService")
     */
    public function updateClassroom(Request $request,$id)
    {
        $Service = $this->getDoctrine()->getRepository(Service::class)->find($id);
        $form = $this->createForm(ServiceType::class, $Service);
        //$form->add('modifier',SubmitType::class);
        $form->handleRequest($request);
        if (($form->isSubmitted())&&($form->isValid())) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('listService');
        }
        return $this->render("Back/update.html.twig",array('form'=>$form->createView()));
    }

//search
  /**
     * @param Request $request
     * @param NormalizerInterface $normalizer
     * @return JsonResponse
     * @throws ExceptionInterface
     * @Route("/search",name="search")
     */

    public function search(Request $request,NormalizerInterface $normalizer){
        $repository = $this->getDoctrine()->getRepository(Service::class);
        $requestString = $request->get('searchValue');
        $Recuperation = $repository->findBynomrec($requestString);
        $jsonContent = $normalizer->normalize($Recuperation, 'json', ['name' => 'Service:read']);
        $re = json_encode($jsonContent);
       
        return new Response($re);

    }
        /**
     * @Route("/mapbox", name="mapbox")
     */
    public function mapbox(Request $request): Response
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
            'id' => $student->getId(),  
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
        return $this->render('Front/mapbox.html.twig',array('json_map'=> $jsonData,'tot'=> $map)); 
     } else { 
        return $this->render('Front/mapbox.html.twig',array('tot'=> $map)); 
     } 
  
    }
/**
     * @Route("/AddServbox", name="AddServbox")
     */
    public function backmapbox(Request $request): Response
    {
        $map = new Service();
            $formm = $this->createForm(ServiceType::class, $map);
            $formm->handleRequest($request);
          
            if (($formm->isSubmitted())&&($formm->isValid())) {
                $file=$formm->get('Image')->getData();
               $filename=md5(uniqid()).'.'.$file->guessExtension();
               $file->move($this->getParameter('images_directory'),$filename);
                $em = $this->getDoctrine()->getManager();
               $map->setImage($filename);
                $em->persist($map);
                $em->flush();
                return $this->redirectToRoute('listService');
            }
            return $this->render("Back/mapbox.html.twig",array('formm'=>$formm->createView()));
    }

 /**
     * @param ServiceRepository $repository
     * @return Response
     * @Route("/pdfbox",name="pdfbox",methods={"GET"})
     */
    public function pdf(ServiceRepository $repository):Response{
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $Recuperations=$repository->findAll();
        // Retrieve the HTML generated in our twig file
        $html=$this->renderView('Back/pdfbox.html.twig',[
            'Recuperations'=>$Recuperations
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);
// (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("boxpdf.pdf", [
            "Attachment" => true
        ]);

    }

    //////sort///
/**
     * @param ServiceRepository $repository
     * @return Response
     * @Route ("/trinombox",name="trinombox")
     */
    public function orederByNom(ServiceRepository $repository){
        $services = $this->getDoctrine()->getRepository(Service::class)->OrderBynom();
        return $this->render('Back/affichee.html.twig',
            ['Services' => $services]);
    }
//////single service///
/**
     * @Route("/singleServ/{id}", name="singleServ")
     */
    public function singleServ(Request $request,$id)
    {
        $Service = $this->getDoctrine()->getRepository(Service::class)->find($id);
        return $this->render('Front/single-service.html.twig',array('Serv'=> $Service)); 
    }

}