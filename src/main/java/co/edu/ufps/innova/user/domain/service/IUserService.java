package co.edu.ufps.innova.user.domain.service;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.user.domain.dto.User;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
public interface IUserService {

    /**
     * Method for save an User
     *
     * @param user to save
     * @return the saved User
     */
    User save(User user);

    /**
     * Method for update an User
     *
     * @param id   of User
     * @param user to update
     * @return true if update the User
     */
    boolean update(String id, User user);

    /**
     * Method for list all Users
     *
     * @return all Users
     */
    List<User> findAll();

    /**
     * Method for get an User by id
     *
     * @param id of the User
     * @return the User with the given id
     */
    Optional<User> findById(String id);

    /**
     * Method for delete an User
     *
     * @param id of the User
     * @return true if delete the User
     */
    boolean delete(String id);

    /**
     * Method for get the encrypted password of an User by id
     *
     * @param id of the User
     * @return the encrypted password
     */
    String getPassword(String id);

    /**
     * Method for get an User by email
     *
     * @param email of the User
     * @return the User with the given email
     */
    Optional<User> findByEmail(String email);

    /**
     * Method for change the state of an User
     *
     * @param id of the User
     * @return true if change the state of the User
     */
    boolean changeState(String id);

    /**
     * Method for change the password of an User by id
     *
     * @param id          of the User
     * @param oldPassword to will be replaced
     * @param newPassword to will be persisted
     * @return true if the password was changed
     */
    boolean changePassword(String id, String oldPassword, String newPassword);

    /**
     * Method for change the password of an User and send him an email with the new password
     *
     * @param email of the User
     * @return true if change the password and the email was sent
     */
    boolean recoverPassword(String email);

}
