package com.example.cmpt276project.services;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmailAddress;

    @Autowired
    // Constructor
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // Send an email
    public void sendEmail(String toEmailAddress, String emailSubject, String emailText)
    {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name()
            );

            mimeMessageHelper.setFrom(fromEmailAddress);
            mimeMessageHelper.setTo(toEmailAddress);
            mimeMessageHelper.setSubject(emailSubject);
            mimeMessageHelper.setText(emailText);

            javaMailSender.send(mimeMessage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}