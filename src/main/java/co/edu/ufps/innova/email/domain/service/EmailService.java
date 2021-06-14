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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendNewUserEmail(String userEmail, String newPassword) {
        Email email = new Email();
        email.setTo(userEmail);
        email.setSubject("Innova - Registro exitoso");
        email.setContent(String.format("Con esta contraseña podrá ingresar a la plataforma: %s recomendamos " +
                "cambie esta contraseña desde la plataforma apenas ingrese a la misma.", newPassword));
        return sendEmailTool(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendRecoverPasswordEmail(String userEmail, String newPassword) {
        Email email = new Email();
        email.setTo(userEmail);
        email.setSubject("Innova - Cambio de contraseña");
        email.setContent(String.format("Con esta nueva contraseña podrá ingresar a la plataforma: %s " +
                        "recomendamos cambie esta contraseña desde la plataforma apenas ingrese a la misma.",
                newPassword));
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
        Email email = new Email();
        email.setTo(clientEmail);
        email.setSubject("Innova - Asesoría agendada");
        email.setContent(String.format("Hola %s, fuiste agendado(a) para una asesoría con %s %s para el día %s " +
                        "a la(s) %s. Revisala desde la aplicación; en caso de cualquier inquietud puedes " +
                        "contactarte con tu asesor en el siguiente correo:  %s.",
                clientName,
                consultantName,
                consultantLastName,
                advisoryDate,
                advisoryHour,
                consultantEmail));
        return sendEmailTool(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendUpdatedAdviceEmail(String clientEmail, String consultantEmail, String advisoryDate) {
        Email email = new Email();
        email.setTo(clientEmail);
        email.setSubject("Innova - Asesoría modificada");
        email.setContent(String.format("La asesoría en la que estás agendado(a) para el día %s fue modificada, " +
                        "puedes validarla en la aplicación y en caso de cualquier inquietud puedes contactarte " +
                        "con tu asesor en el siguiente correo: %s.",
                advisoryDate,
                consultantEmail));
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

}
