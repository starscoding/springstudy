package com.azxx.demon.web.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Smile on 2018/9/27.
 */
@RestController
@RequestMapping("mail")
public class MailController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MailSender mailSender;

    @Value("${mail.from}")
    private String emailFrom;

    @RequestMapping(value = "send",method = RequestMethod.GET)
    public String sendMail(){
        System.out.println(emailFrom);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("hx0011001@hengxinyongli.com");
        message.setFrom(emailFrom);
        message.setSubject("test email");
        message.setText("this is text email,please ignore it!thank you !");
        String result=null;
        try{
            mailSender.send(message);
            result = "the email has been send!";
        }catch (Exception e){
            logger.error("",e);
//            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "sendWithAttach",method = RequestMethod.GET)
    public String sendWithAttach(){
        return null;
    }

    public static void main(String[] args) {

        final String username = "mr.xuzheng@outlook.com";
        final String password = "shouwang-13";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("hx0011001@hengxinyongli.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);
            System.out.println("Done");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
