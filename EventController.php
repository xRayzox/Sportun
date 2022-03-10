<?php

namespace App\Controller;

use App\Form\EventType;
use App\Entity\Event;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\EventRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use DateTime;
class EventController extends AbstractController
{
   
      /**
     * @Route("/event", name="eventall")
     */
    public function index(EventRepository $EventRepository): Response
    {
        return $this->render('event/Affiche.html.twig', [
            'Events' => $EventRepository->findAll(),
        ]);
    }

    /**
     * @Route("/event/newevent", name="event_new")
     */
    public function new(Request $request): Response
    {
        $event = new Event();
        $form = $this->createForm(EventType::class, $event);
        $form->add('Ajouter', SubmitType::class);
        $form->handleRequest($request);//traiter la requette recu
        
        if (($form->isSubmitted()) && ($form->isValid())) {//isvalid pour control de saisie(les entités)
            $file=$form->get('image')->getData();
            $filename=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('images_directory'),
            $filename);
            
            $entityManager = $this->getDoctrine()->getManager();
            $event->setImage($filename);
            $entityManager->persist($event);//persist=insert into
            $entityManager->flush();

            return $this->redirectToRoute('event_show');
        }

        return $this->render('event/index.html.twig', array
           (
            'form' => $form->createView(),
        ));
    }

    /**
     * @Route("/event/list", name="event_show")
     */
    public function show(EventRepository $EventRepository): Response
    {
        return $this->render('event/affiche.html.twig', [
            'Events' => $EventRepository->eveall(),
        ]); 
    }
/**
     * @Route("/event/liste", name="event_showF")
     */
    public function showFront(request $request , PaginatorInterface $paginator)
    {
         // Méthode findBy qui permet de récupérer les données avec des critères de filtre et de tri
         $donnees = $this->getDoctrine()->getRepository(Event::class)->findBy([]);

         $Event = $paginator->paginate(
             $donnees, // Requête contenant les données à paginer (ici nos articles)
             $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
             1 // Nombre de résultats par page
         );
        //$Events= $paginator->paginate
        return $this->render('Front/index.html.twig', [
            'Events' => $Event,
        ]); 
    }
    /**
     * @Route("/{id}/detail", name="detail_show")
     * 
     */
    public function detail(EventRepository $EventRepository,$id): Response
    {
        //$event = $this->getDoctrine()->getRepository(Event::class)->find($id);
        return $this->render('Front/detail.html.twig', [
            'Events' => $EventRepository->find($id)
        ]); 
    }
   /**
     * @Route("/{id}/eddit", name="updateevent")
     */
    public function updateevent(Request $request,$id)
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->find($id);
        $form = $this->createForm(EventType::class, $event);
        $form->add('modifier',SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('event_show');
        }
        return $this->render("event/Edit.html.twig",array('form'=>$form->createView()));
    }
 /**
     * @Route("/{id}/wishlist", name="updatewishliste")
     */
    public function updatewishliste(Request $request,$id)
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->find($id);
        $form = $this->createForm(EventType::class, $event);
        $form->add('modifier',SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('event_show');
        }
        return $this->render("event/Edit.html.twig",array('form'=>$form->createView()));
    }
    /**
     * @Route("/deleteevent/{id}", name="deleteevent")
     */
    public function deleteevent($id)
    {
        $event = $this->getDoctrine()->getRepository(event::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($event);
        $em->flush();
        return $this->redirectToRoute('event_show');
    }

    /*public function testIndex()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/');

        $this->assertEquals(200, $client->getResponse()->getStatusCode());
        $this->assertContains('Welcome to Symfony', $crawler->filter('#container h1')->text());
    }*/
    /**
     * @Route("/calendar", name="main")
     */
    public function indexcalendar(EventRepository $calendar)
    {
        $events = $calendar->findAll();

        $rdvs = [];

        foreach($events as $event){
            $rdvs[] = 
            [
               // 'title' => $event->getnameevent(),
               'id' => $event->getId(),
                'start' => $event->getStar()->format('Y-m-d H:i:s'),
                'end' => $event->getEnd()->format('Y-m-d H:i:s'),
                'title' => $event->getnameevent(),
                'backgroundColor' => $event->getBackgroundColor(),
                'borderColor' => $event->getBorderColor(),
                'textColor' => $event->getTextColor(),
                'allDay' => $event->getAllDay(),
            ];
        }

        $data = json_encode($rdvs);

        return $this->render('calendar/index.html.twig', compact('data'));
    }

/**
     * @param Request $request
     * @param NormalizerInterface $normalizer
     * @return JsonResponse
     * @throws ExceptionInterface
     * @Route("/search",name="search")
     */

    public function search(Request $request,NormalizerInterface $normalizer,EventRepository $repository){
        
        $requestString = $request->get('searchValue');
        $Recuperation = $repository->findByname($requestString);
        
        $jsonContent = $normalizer->normalize($Recuperation, 'json', ['name' => 'Event:read']);
        $re = json_encode($jsonContent);
       
        return new Response($re);


    }
    /**
     * @Route("/trititre", name="trititre")
     */

    public function Trititre(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $query = $em->createQuery(
            'SELECT e1 FROM App\Entity\Entrainement e1 
            ORDER BY e1.titre'
        );


        $rep = $query->getResult();

        return $this->render('Entrainement/affichform-front.html.twig',
            array('Entrainement' => $rep));

    }
}