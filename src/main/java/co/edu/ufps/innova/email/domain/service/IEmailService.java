package co.edu.ufps.innova.email.domain.service;

import co.edu.ufps.innova.email.domain.dto.Email;

public interface IEmailService {

    /**
     * Method for send a email
     *
     * @param emailBody to send
     * @return true if the email was send
     */
    boolean sendEmail(Email emailBody);

}
