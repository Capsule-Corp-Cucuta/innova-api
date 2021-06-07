package co.edu.ufps.innova.authentication.domain.repository;

public interface IPasswordRepository {

    /**
     * Method for generate a new password without encryption
     *
     * @return random String
     */
    String generatePassword();

    /**
     * Method for encrypt any String
     *
     * @param password String to encrypt
     * @return String encrypted with bCrypt
     */
    String encryptPassword(String password);

    /**
     * Method for validate two Strings with bCrypt
     *
     * @param actualPassword String without encrypt
     * @param userPassword   String encrypted, actual password
     * @return true if Strings are the same under encrypt
     */
    boolean validatePassword(String actualPassword, String userPassword);

}
