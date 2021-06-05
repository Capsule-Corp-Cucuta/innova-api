package co.edu.ufps.innova.authentication.domain.repository;

public interface IPasswordRepository {

    String generatePassword();

    String encryptPassword(String password);

    boolean validatePassword(String actualPassword, String userPassword);

}
