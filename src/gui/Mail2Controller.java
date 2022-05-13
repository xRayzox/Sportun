/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class Mail2Controller  {

    @FXML
    public PasswordField password;
    @FXML
    public TextField EmailTo;
    @FXML
    public TextField EmailFrom;
    @FXML
    public TextField subject;
    @FXML
    public TextArea msg;
    @FXML
    public Button sendbtn;
    public Button exitbtn;
    @FXML
    public Label msgSent;
    @FXML
    private StackPane StckFrontProduit;
    @FXML
    private Pane PaneBlur;
    @FXML
    private Button sendbtn1;

    
    @FXML
    public void buttonClicked(ActionEvent actionEvent){
         System.out.println("prepered to send mail!");
        sendEmail();
    }
    public void ExitbuttonClicked(ActionEvent actionEvent){
        System.exit(0);
    }
    public void sendEmail(){
        System.out.println("prepered to send mail!");
        
        String to= EmailTo.getText();
        String from = EmailFrom.getText();
        String host= "smtp.gmail.com";
        final String username = EmailFrom.getText();
        final String passwordd = password.getText();
    
        //setup mail
        Properties props = System.getProperties();
        
        props.put("mail.smtp.auth", true);
         props.put("mail.smtp.starttls.enable",true);
         props.put("mail.smtp.ssl","tls");
       // props.put("mail.smtp.starttls.emails","true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
       
         props.put("mail.smtp.ssl.trust","*");
       // props.put("smtpd_tls_security-level","may");
        
         
        /*props.put("mail.smtp.host", mailAccount.getMailHost());
props.put("mail.smtp.port", mailAccount.getPort());
props.put("mail.smtp.auth", mailAccount.isAuth());
props.put("mail.smtp.starttls.enable",mailAccount.isStartTls());*/
        //props.setTLS(true);
        Session session = Session.getDefaultInstance(props, new Authenticator(){ 
       
        @Override
        protected PasswordAuthentication getPasswordAuthentication(){
             
            return new PasswordAuthentication("nedrabenyoussef9@gmail.com", "fstgtpkpzmyuayew");
        }});
try{

            //create mail
            MimeMessage m = new MimeMessage(session);
         
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject.getText());
            m.setText(msg.getText());

            //send mail

            Transport.send(m);
            msgSent.setVisible(true);
            System.out.println("Message sent!");
             TrayNotification tray= new TrayNotification();
        AnimationType anim = AnimationType.POPUP;
        tray.setAnimationType(anim);
        tray.setTitle("Notifications");
        tray.setMessage("Votre mail à été envoyer avec succès");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));

        }   catch (Exception e){
            e.printStackTrace();
        }

   /* private static Message prepareMessage(Session session, String to,String recepient, String is_verified ){
 
        try {
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Activation du compte");
            message.setText("wellcome to HayaCampi click on the link below to activate your acount \n http://127.0.0.1:8000/activation/"+is_verified);
            System.out.println("Message set succfuly");
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    return null;
    }*/
    
    
    
    }
    @FXML
    private void GoToFournisseur(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("EventView.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }

    @FXML
    private void GoToProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("PieChart2.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }

    @FXML
    private void GoToHomeProduit(MouseEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("Mail2.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }
 @FXML
    private void gotocategorie(MouseEvent event) throws IOException {
          Parent menu = FXMLLoader.load(getClass().getResource("CategorieView.fxml"));
        StckFrontProduit.getChildren().removeAll();
        StckFrontProduit.getChildren().setAll(menu);
    }
    @FXML
    private void CloseWindowClicked(MouseEvent event) {
        System.exit(0);
    }

      
    
}

