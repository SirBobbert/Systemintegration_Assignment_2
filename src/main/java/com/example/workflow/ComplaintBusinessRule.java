package com.example.workflow;

import jakarta.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Named
public class ComplaintBusinessRule implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String complaint = (String) delegateExecution.getVariable("complaint");

        if (complaint != null && complaint.length() > 100) {
            // Perform actions for a sufficient complaint
            delegateExecution.setVariable("sufficientComplaint", "yes");
            System.out.println("Complaint is sufficient");
        } else {
            // Perform actions for an insufficient complaint
            delegateExecution.setVariable("sufficientComplaint", "no");
            System.out.println("Complaint is insufficient");
        }

    }
}
