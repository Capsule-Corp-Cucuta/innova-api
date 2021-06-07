package co.edu.ufps.innova.email.domain.service;

import co.edu.ufps.innova.email.domain.dto.Email;

public interface IEmailService {

    boolean sendEmail(Email emailBody);

}
