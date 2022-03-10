<?php

namespace App\Controller;

use App\Entity\Event;
use DateTime;
use Doctrine\ORM\Mapping\Id;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ApiController extends AbstractController
{
    /**
     * @Route("/api", name="api")
     */
    public function index()
    {
        return $this->render('api/index.html.twig', [
            'controller_name' => 'ApiController',
        ]);
    }

    /**
     * @Route("/api/{id}/edit", name="api_event_edit", methods={"GET"})
     */
    public function majEvent(?Event $Event, Request $request)
    {
        // On récupère les données
        $donnees = json_decode($request->getContent());

        if(
            isset($donnees->title) && !empty($donnees->title) &&
            isset($donnees->start) && !empty($donnees->start) &&
            isset($donnees->descriptionEvent) && !empty($donnees->descriptionEvent) &&
            isset($donnees->backgroundColor) && !empty($donnees->backgroundColor)&&
            isset($donnees->borderColor) && !empty($donnees->borderColor) &&
            isset($donnees->textColor) && !empty($donnees->textColor)
        ){
            // Les données sont complètes
            // On initialise un code
            $code = 200;

            // On vérifie si l'id existe
            if(!$Event){
                // On instancie un rendez-vous
                $Event = new Event;

                // On change le code
                $code = 201;
            }

            // On hydrate l'objet avec les données
            $Event->setnameevent($donnees->title);
            $Event->setDescriptionEvent($donnees->description);
            $Event->setStar(new DateTime($donnees->start));
            if($donnees->allDay){
                $Event->setEnd(new DateTime($donnees->start));
            }else{
                $Event->setEnd(new DateTime($donnees->end));
            }
            $Event->setAllDay($donnees->allDay);
            $Event->setBackgroundColor($donnees->backgroundColor);
            $Event->setBorderColor($donnees->borderColor);
            $Event->setTextColor($donnees->textColor);
           // $Event->setPromotion($donnees->allDay);
         
           // $Event->setBorderColor($donnees->borderColor);
           // $Event->setTextColor($donnees->textColor);

            $em = $this->getDoctrine()->getManager();
            $em->persist($Event);
            $em->flush();

            // On retourne le code
            return new Response('Ok', $code);
        }else{
            // Les données sont incomplètes
            return new Response('Données incomplètes', 404);
        }


        return $this->render('api/index.html.twig', [
            'controller_name' => 'ApiController',
        ]);
    }
}
