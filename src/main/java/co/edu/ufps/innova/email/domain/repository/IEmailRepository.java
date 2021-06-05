package co.edu.ufps.innova.email.domain.repository;

import co.edu.ufps.innova.email.domain.dto.Email;

public interface IEmailRepository {

    boolean sendEmail(Email emailBody);

}
