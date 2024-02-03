package com.ecommerce.ecommerce.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSetPasswordEmail(String email, String token) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setFrom("alexandruvictorcerna@yahoo.com");
            mimeMessageHelper.setSubject("Reset Password");
            mimeMessageHelper.setText("""
        <div>
          <a href="http://localhost:4200/set-password?token=%s" target="_blank">Click the link to reset your password</a>
        </div>
        """.formatted(token), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}