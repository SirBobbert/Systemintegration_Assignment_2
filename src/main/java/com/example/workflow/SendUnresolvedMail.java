package com.example.workflow;

import jakarta.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Named
public class SendUnresolvedMail implements JavaDelegate {

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
            message.setSubject("Re: Your Submitted Complaint");

            // Create a StringBuilder to construct the email body
            StringBuilder emailBody = new StringBuilder();
            emailBody.append("Dear ").append(complainerName).append(",\n\n");
            emailBody.append("We hope this message finds you well.\n\n");
            emailBody.append("We would like to inform you that your previously submitted complaint has been reviewed, and we regret to inform you that it remains unresolved.\n\n");
            emailBody.append("Complaint Details:\n");
            emailBody.append(complaint).append("\n\n");
            emailBody.append("We understand your concerns and apologize for any inconvenience caused. Our team is actively working to address your complaint and will reach out to you as soon as possible with updates.\n\n");
            emailBody.append("Thank you for your patience and understanding. If you have any further questions or require immediate assistance, please feel free to contact our support team.\n\n");
            emailBody.append("Sincerely,\n");
            emailBody.append("Your Company Support Team");

            // Set the email message content
            message.setText(emailBody.toString());

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        delegateExecution.setVariable("emailSent", true);
    }
}
