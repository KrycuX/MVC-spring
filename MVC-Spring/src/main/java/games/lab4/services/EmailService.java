package games.lab4.services;

import javax.mail.MessagingException;

public interface EmailService {
    void sendSimpleMessage(String to, String Subject, String text);
    void sendMimeMessage(String to, String Subject, String text) throws MessagingException;
   // void sendPDF(String to, String subject, String text) throws MessagingException;
}
