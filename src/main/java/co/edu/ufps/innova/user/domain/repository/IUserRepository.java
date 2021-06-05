package co.edu.ufps.innova.user.domain.repository;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.user.domain.dto.User;

public interface IUserRepository {

    User save(User user, String password);

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    void delete(User user);

    boolean changeState(String id);

    String getUserType(String id);

    String getPassword(String id);

}
