<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use App\Form\ContactType;

class ContactController extends AbstractController
{
    /**
     * @Route("/contact", name="contact")
     */
    public function index(Request $request,\Swift_Mailer $mailer): Response
    {
        $form = $this->createForm(ContactType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $contact = $form->getData();
//dd($contact);
$message = (new \Swift_Message('Nouveau contact'))
    // On attribue l'expéditeur
    ->setFrom($contact['email'])
   // ->setFrom('nedra.benyoussef@esprit.tn')
    ->setTo('nedra.benyoussef@esprit.tn')
    
    // On crée le texte avec la vue
    ->setBody(
        $this->renderView(
            'emails/contact.html.twig', compact('contact')
        ),
        'text/html'
    );
    $mailer->send($message);
    $this->addFlash('message', 'Votre message a été transmis, nous vous répondrons dans les meilleurs délais.');
            return $this->redirectToRoute('event_show');
             // Permet un message flash de renvoi
        }
        return $this->render('contact/index.html.twig',['contactForm' => $form->createView()]);
    }}

