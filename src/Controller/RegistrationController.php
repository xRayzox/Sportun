<?php

namespace App\Controller;

use App\Entity\User;
use Twilio\Rest\Client;
use App\Form\RegistrationFormType;
use App\Repository\UserRepository;
use App\Security\AppCustomAuthenticator;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Notifier\TexterInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Notifier\Message\SmsMessage;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Security\Guard\GuardAuthenticatorHandler;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

class RegistrationController extends AbstractController
{
    /**
     * @Route("/register", name="app_register")
     */
    public function register(Request $request, UserPasswordEncoderInterface $userPasswordEncoder, GuardAuthenticatorHandler $guardHandler, AppCustomAuthenticator $authenticator, EntityManagerInterface $entityManager, MailerInterface $mailer, Client $twilio): Response

    {


        $sid    = "AC575e044a0314036700d4bfdacff5aceb";
        $token  = "[Redacted]";
        $twilio = new Client("AC8f2c8d004faaaee5d9a5c15bfcebfa02", "0bba1ea8df3a23d4ae53cd40a34d62bf");




        $user = new User();
        $form = $this->createForm(RegistrationFormType::class, $user);
        $form->handleRequest($request);

        if (count($request->request->all())) {
            // encode the plain password
            $user->setPassword(
                $userPasswordEncoder->encodePassword(
                    $user,
                    $form->get('plainPassword')->getData()
                )
            );
            if ($form->get('Role')->getData() != "client") {
                # code...
                $diplome = $form->get('diplome')->getData();

                // this condition is needed because the 'brochure' field is not required
                // so the PDF file must be processed only when a file is uploaded
                if ($diplome) {
                    $originalFilename = pathinfo($diplome->getClientOriginalName(), PATHINFO_FILENAME);
                    // this is needed to safely include the file name as part of the URL
                    $safeFilename = $originalFilename;
                    $newFilename = $safeFilename . '-' . uniqid() . '.' . $diplome->guessExtension();

                    // Move the file to the directory where brochures are stored
                    try {
                        $diplome->move(
                            $this->getParameter('diplomes_directory'),
                            $newFilename
                        );
                    } catch (FileException $e) {
                        // ... handle exception if something happens during file upload
                    }

                    // updates the 'brochureFilename' property to store the PDF file name
                    // instead of its contents
                    $user->setDiplome($newFilename);
                }else{
                    $this->addFlash("alert4" , "Seul un coach ou nutritionniste peuvent uploader leur diplome !");
                    $this->redirectToRoute("app_register");
                }
            }
            $user->setActivationToken(md5(uniqid()));
            $user->setStatus("ENABLED");
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($user);
            $entityManager->flush();
            // do anything else you need here, like send an email


            $message = (new \Swift_Message('Nouveau compte'))
                ->setFrom('votre@adresse.fr')
                ->setTo($user->getEmail())
                ->setBody(
                    $this->renderView(
                        'email/activation.html.twig',
                        ['token' => $user->getActivationToken()]
                    ),
                    'text/html'
                );
            $mailer->send($message);
            $client = new Client("AC575e044a0314036700d4bfdacff5aceb", "072c6e1972579777331c7edf67c7e026");
            $client->messages->create(
                // Where to send a text message (your cell phone?)
                '+21693608708',
                array(
                    'from' => "+1 435 260 4046",
                    'body' => 'Inscription faite avec succés'
                )
            );
            return $guardHandler->authenticateUserAndHandleSuccess(
                $user,
                $request,
                $authenticator,
                'main' // firewall name in security.yaml
            );
        }

        return $this->render('registration/register.html.twig', [
            'registrationForm' => $form->createView(),
        ]);
    }

    /**
     * @Route("/activation/{token}", name="activation")
     */
    function activation($token, UserRepository $users)
    {
        // On recherche si un utilisateur avec ce token existe dans la base de données
        $user = $users->findOneBy(['activation_token' => $token]);
        //dd($user);
        // Si aucun utilisateur n'est associé à ce token
        if (!$user) {
            // On renvoie une erreur 404
            throw $this->createNotFoundException('Cet utilisateur n\'existe pas');
        }

        // On supprime le token
        $user->setActivationToken(null);
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($user);
        $entityManager->flush();


        $this->addFlash('message', 'Vous avez bien activé votre compte');

        // return $this->redirectToRoute('home');
        return new response("utilisateur activé avec succès");
    }
}
