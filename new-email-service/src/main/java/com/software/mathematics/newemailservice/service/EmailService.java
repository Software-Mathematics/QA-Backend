package com.software.mathematics.newemailservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendMail(String to, String[] toArr, String[] cc, String[] bcc, String subject, String body) {
        System.out.println("==============starting send mail ===============");
        System.out.println("sending...");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            if (toArr != null && toArr.length > 0) {
                mimeMessageHelper.setTo(toArr);
            } else if (to != null) {
                mimeMessageHelper.setTo(to);
            } else {
                mimeMessageHelper.setTo("");
            }
            mimeMessageHelper.setFrom(from);
            if (cc != null && cc.length > 0) {
                mimeMessageHelper.setCc(cc);
            }
            mimeMessageHelper.setText(body, true);
            mimeMessageHelper.setSubject(subject);
            javaMailSender.send(mimeMessage);
            System.out.println("mail sent successfully !!");
            System.out.println("==============ending send mail ===============");
        } catch (Exception e) {
            throw new IllegalStateException("failed to send mail... :-  " + e);
        }
    }
}
