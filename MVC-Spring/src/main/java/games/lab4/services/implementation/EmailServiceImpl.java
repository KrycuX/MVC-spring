package games.lab4.services.implementation;

import games.lab4.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailServiceImpl implements EmailService {
@Autowired
private JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(String to, String Subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("yuutatogashi@o2.pl");
        message.setTo(to);
        message.setSubject(Subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendMimeMessage(String to, String Subject, String text) throws MessagingException {
        var mimeMessage=emailSender.createMimeMessage();
        var helper=new MimeMessageHelper(mimeMessage, "utf-8");
         helper.setFrom("yuutatogashi@o2.pl");
        helper.setTo(to);
        helper.setSubject(Subject);
        helper.setText(text,true);
        emailSender.send(mimeMessage);


    }
}
