package co.edu.ufps.innova.email.domain.service;

import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.email.domain.dto.Email;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {

    private final JavaMailSender sender;

    @Override
    public boolean sendEmail(Email emailBody) {
        return sendEmailTool(emailBody.getContent(), emailBody.getTo(), emailBody.getSubject());
    }

    private boolean sendEmailTool(String textMessage, String email, String subject) {
        boolean send = false;
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(email);
            helper.setText(textMessage, true);
            helper.setSubject(subject);
            sender.send(message);
            send = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return send;
    }

}
