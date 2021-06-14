package co.edu.ufps.innova.email.domain.service;

public interface IEmailService {

    /**
     * Method for send an Email to the new User with the password for login
     *
     * @param userEmail   to send
     * @param newPassword of User
     * @return true if the Email was sent
     */
    boolean sendNewUserEmail(String userEmail, String newPassword);

    /**
     * Method for send an Email to the User when forgot his password
     *
     * @param userEmail   to send
     * @param newPassword of User
     * @return true if the Email was sent
     */
    boolean sendRecoverPasswordEmail(String userEmail, String newPassword);

    /**
     * Method for send an Email to the client when the consultant schedule an advisory
     *
     * @param clientName         of Client
     * @param clientEmail        of Client
     * @param consultantName     of Consultant
     * @param consultantLastName of Consultant
     * @param consultantEmail    of Consultant
     * @param advisoryDate       of Advisory
     * @param advisoryHour       of Advisory
     * @return true if the Email was sent
     */
    boolean sendScheduledAdviceEmail(String clientName,
                                     String clientEmail,
                                     String consultantName,
                                     String consultantLastName,
                                     String consultantEmail,
                                     String advisoryDate,
                                     String advisoryHour);

    /**
     * Method for send and Email to the client when the consultant update the advisory
     *
     * @param clientEmail     of Client
     * @param consultantEmail of Consultant
     * @param advisoryDate    of Advisory
     * @return true if the Email was sent
     */
    boolean sendUpdatedAdviceEmail(String clientEmail, String consultantEmail, String advisoryDate);

}
