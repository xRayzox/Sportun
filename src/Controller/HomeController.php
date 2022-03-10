<?php

namespace App\Controller;

use App\Entity\User;
use Twilio\Rest\Client;
use App\Form\RegistrationFormType;
use App\Repository\UserRepository;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

use Symfony\Component\HttpFoundation\Session\Session;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Mime\Email;





class HomeController extends AbstractController
{
    /**
     * @Route("/test", name="test")
     */
    public function index(UserRepository $UserRepository): Response
    {
        return $this->render('Back\utilisateur.html.twig', [
            'users' => $UserRepository->findAll(),
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
     * @Route("/admin/{id}/eddit", name="/admin/updateuser")
     */
    public function updateuser(Request $request, $id)
    {
        $user = $this->getDoctrine()->getRepository(User::class)->find($id);
        $form = $this->createForm(RegistrationFormType::class, $user);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('user_show');
        }
        return $this->render("Back/Edit.html.twig", array('form' => $form->createView()));
    }

    /**
     * @Route("/admin/deleteuser/{id}", name="/admin/deleteuser")
     */
    public function deleteuser($id)
    {
        $user = $this->getDoctrine()->getRepository(User::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute('user_show');
    }

    /**
     * @Route("/reset/password" , name="reset_password")
     */

    public function reset(Request $request,MailerInterface $mailer)
    {
        $session = $request->getSession();
        
        if ($request->isMethod('GET')) {
            $pass = substr(md5(uniqid(mt_rand(), true)), 0, 8);
            $session->set('code', $pass);
            $client = new Client("AC575e044a0314036700d4bfdacff5aceb", "072c6e1972579777331c7edf67c7e026");
            $curlOptions = [CURLOPT_SSL_VERIFYHOST => false, CURLOPT_SSL_VERIFYPEER => false];
            $client->messages->create(
                // Where to send a text message (your cell phone?)
                '+21654889484',
                array(
                    'from' => "+1 435 260 4046",
                    'body' => 'Votre code de réinitialisation est ' . $pass . ''
                )
            );
            $email = (new Email())
            ->from('achwek.harizi1998@gmail.com')
            ->to('achwek.harizi1998@gmail.com')
            //->cc('cc@example.com')
            //->bcc('bcc@example.com')
            //->replyTo('fabien@example.com')
            //->priority(Email::PRIORITY_HIGH)
            ->subject('Reset mot de passe')
            ->text('Votre code de réinitialisation de mot de passe est '.$pass);

        $mailer->send($email);
            return $this->render("home/reset.html.twig");
        }

        if ($request->isMethod('POST')) {
            $code = $request->request->get('code');
            $email=$request->request->get('email');
            $sentCode = $session->get('code');
            if($code == $sentCode){
                return $this->render("home/changepass.html.twig",[
                    'email'=>$email
                ]);
            }else{
                $this->addFlash("alert" , "Code incorrect");
                return $this->redirectToRoute("app_login");
            }
        }
       
        
    }

    /**
     * @Route("/changepass" , name="change_pass")
     */
    public function change(Request $request,UserPasswordEncoderInterface $userPasswordEncoder , UserRepository $userRepository){
        $user = $userRepository->findOneBy(['email' => $request->request->get('email')]);
        if ($request->request->get('pass1') == $request->request->get('pass2')) {
            $user->setPassword(
                $userPasswordEncoder->encodePassword(
                    $user,
                    $request->request->get('pass1')
                )
            );
            $this->getDoctrine()->getManager()->flush();
        }else{
            $this->addFlash("alert1" , "Mots de passe ne sont pas identiques");
            return $this->redirectToRoute("app_login");
        }
        $this->addFlash("success" , "Mot de passe changé");
        return $this->redirectToRoute("home");
    }

    /**
     * @Route("/search" , name="search")
     */
    public function search(Request $request , UserRepository $userRepository){
        $query = $request->request->get('query');

        $qb = $this->getDoctrine()->getManager()->createQueryBuilder();
            $qb->select('u')
                ->from(User::class, 'u')
                ->where('u.nom LIKE :nom')
                ->setParameter('nom', $query);
            $users = $qb->getQuery()->getResult();
        
        return $this->render('Back/utilisateur.html.twig', [
            'users' => $users,
        ]);
    }

    /**
     * @Route("/show/{id}"  , name="show")
     */

     public function showUser(User $user , Request $request){
         
         return $this->render("home/show.html.twig",[
             'user'=> $user
         ]);
     }
}
