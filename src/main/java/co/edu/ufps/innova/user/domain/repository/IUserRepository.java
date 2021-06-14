package co.edu.ufps.innova.user.domain.repository;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.user.domain.dto.User;

public interface IUserRepository {

    /**
     * Method for persist an User
     *
     * @param user     to save
     * @param password of User
     * @return the saved User
     */
    User save(User user, String password);

    /**
     * Method for update info of an User
     *
     * @param id   of User
     * @param user to update
     * @return true if User was updated
     */
    boolean update(String id, User user);

    /**
     * Method for list all Users
     *
     * @return all Users
     */
    List<User> findAll();

    /**
     * Method for find an User by id
     *
     * @param id of User
     * @return User with the given id or Optional
     */
    Optional<User> findById(String id);

    /**
     * Method for delete an User
     *
     * @param user to delete
     */
    void delete(User user);

    /**
     * Method for get the user type of an User
     *
     * @param id of the User
     * @return UserType of the User with the given id
     */
    String getUserType(String id);

    /**
     * Method for get the password of an User
     *
     * @param id of the User
     * @return password of the User with the given id
     */
    String getPassword(String id);

    /**
     * Method for find an User by email
     *
     * @param email of the User
     * @return User with the given email or Optional
     */
    Optional<User> findByEmail(String email);

    /**
     * Method for change the state of an User
     *
     * @param id of the User
     * @return true if the state of the user was changed
     */
    boolean changeState(String id);

    /**
     * Method for change the password of an User
     *
     * @param id       of the User
     * @param password new password for the User
     * @return true if the password was changed
     */
    boolean changePassword(String id, String password);

}
