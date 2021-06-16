package co.edu.ufps.innova.email.domain.service;

import java.io.*;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import org.springframework.util.ResourceUtils;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.email.domain.dto.Email;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {

    @Value("${website.path}")
    private String WEBSITE;
    private final JavaMailSender sender;


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendNewUserEmail(String userEmail, String newPassword) {
        String content = getTemplate("signup.html")
                .replace("[password]", newPassword).replace("[website]", WEBSITE);
        Email email = new Email();
        email.setTo(userEmail);
        email.setSubject("Innova - Registro exitoso");
        email.setContent(content);
        return sendEmailTool(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendRecoverPasswordEmail(String userEmail, String newPassword) {
        String content = getTemplate("recovery-password.html")
                .replace("[password]", newPassword).replace("[website]", WEBSITE);
        Email email = new Email();
        email.setTo(userEmail);
        email.setSubject("Innova - Cambio de contraseña");
        email.setContent(content);
        return sendEmailTool(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendScheduledAdviceEmail(String clientName,
                                            String clientEmail,
                                            String consultantName,
                                            String consultantLastName,
                                            String consultantEmail,
                                            String advisoryDate,
                                            String advisoryHour) {
        String content = getTemplate("new-advisory.html")
                .replace("[client-name]", clientName)
                .replace("[consultant-name]", consultantName)
                .replace("[consultant-lastname]", consultantLastName)
                .replace("[consultant-email]", consultantEmail)
                .replace("[date]", advisoryDate)
                .replace("[hour]", advisoryHour)
                .replace("[website]", WEBSITE);
        Email email = new Email();
        email.setTo(clientEmail);
        email.setSubject("Innova - Asesoría agendada");
        email.setContent(content);
        return sendEmailTool(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendUpdatedAdviceEmail(String clientEmail, String consultantEmail, String advisoryDate) {
        String content = getTemplate("advisory-updated.html")
                .replace("[consultant-email]", consultantEmail)
                .replace("[date]", advisoryDate)
                .replace("[website]", WEBSITE);
        Email email = new Email();
        email.setTo(clientEmail);
        email.setSubject("Innova - Asesoría modificada");
        email.setContent(content);
        return sendEmailTool(email);
    }

    /**
     * Method for send an Email
     *
     * @param emailBody to send
     * @return true if the Email was sent
     */
    private boolean sendEmailTool(Email emailBody) {
        boolean send = false;
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(emailBody.getTo());
            helper.setSubject(emailBody.getSubject());
            helper.setText(emailBody.getContent(), true);
            sender.send(message);
            send = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return send;
    }

    private static String getTemplate(String templateName){
        String template = "";
        try {
            String line = null;
            String classpath = String.format("classpath:assets/%s", templateName);
            File file = ResourceUtils.getFile(classpath);
            InputStream in = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                template += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return template;
    }

}
