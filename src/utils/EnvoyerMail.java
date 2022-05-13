/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;


import static java.time.LocalDate.from;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.mail.*;
//import javax.mail.internet.*;

/**
 *
 * @author Lenovo
 */
public class EnvoyerMail {
    
    public TextField emailToField;
    public TextField emailFromField;
   
    public TextArea emailMessageField;
    public TextField emailSubjectField;
    public PasswordField emailPasswordField;
    public Label sentBoolValue;
    
    
    
    
    public void buttonClicked(ActionEvent actionEvent){
        sendEmail();
    }
    public void sendEmail(){
        
        String to= emailToField.getText();
        String from = emailFromField.getText();
        String host= "smtp.gmail.com";
        final String username = emailFromField.getText();
        final String password = emailPasswordField.getText();
        
        //setup mail
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.emails", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){ 
        
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(username, password);
        }
    });

        try{

            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject(emailSubjectField.getText());
            m.setText(emailMessageField.getText());

            //send mail

            Transport.send(m);
            sentBoolValue.setVisible(true);
            System.out.println("Message sent!");

        }   catch (MessagingException e){
            e.printStackTrace();
        }

    
    
    
    
    }
   
    
}
