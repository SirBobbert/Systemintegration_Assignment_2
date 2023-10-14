package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import jakarta.inject.Named;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Named
public class SendSubmissionMail implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // Email sender config
        String myEmail = GlobalVariables.globalEmail;
        String myPassword = GlobalVariables.globalPassword;

        // Email receiver config
        String emailReceiver = (String) delegateExecution.getVariable("email");

        // Other info about complainer
        String complainerName = (String) delegateExecution.getVariable("fullName");
        String complaint = (String) delegateExecution.getVariable("complaint");

        // Gmail SMTP configuration
        String to = emailReceiver; // The recipient's email address
        String from = myEmail; // Your Gmail email address
        String host = "smtp.gmail.com"; // Gmail SMTP server
        String username = myEmail; // Your Gmail email address
        String password = myPassword; // Your Gmail App Password (not your regular Gmail password)

        // Set the properties for the email
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // Use port 587 for TLS
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS

        // Get the Session object and create the email message
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Your complaint was submitted");

            // Create a StringBuilder to construct the email body
            // Create a StringBuilder to construct the email body
            StringBuilder emailBody = new StringBuilder();
            emailBody.append("Dear ").append(complainerName).append("\n");
            emailBody.append("You have submitted the following complaint:\n\n");
            emailBody.append(complaint).append("\n\n");
            emailBody.append("We will reach out to you as soon as possible.");


            // Set the email message content
            message.setText(emailBody.toString());

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");


        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        delegateExecution.setVariable("complaint", complaint);
        delegateExecution.setVariable("fullName", complainerName);
    }
}
