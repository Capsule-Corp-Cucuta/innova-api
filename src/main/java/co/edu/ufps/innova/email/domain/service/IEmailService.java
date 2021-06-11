package co.edu.ufps.innova.email.domain.service;

public interface IEmailService {

    boolean sendNewUserEmail(String userEmail, String newPassword);

    boolean sendRecoverPasswordEmail(String userEmail, String newPassword);

    boolean sendScheduledAdviceEmail(String clientName,
                                 String clientEmail,
                                 String consultantName,
                                 String consultantLastName,
                                 String consultantEmail,
                                 String advisoryDate,
                                 String advisoryHour);

    boolean sendUpdatedAdviceEmail(String clientEmail, String consultantEmail, String advisoryDate);

}
