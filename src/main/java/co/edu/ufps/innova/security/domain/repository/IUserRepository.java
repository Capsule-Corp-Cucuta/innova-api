package co.edu.ufps.innova.security.domain.repository;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.security.domain.dto.User;

public interface IUserRepository {

    User save(User user, String password);

    Optional<User> findById(String id);

    List<User> findAll();

    void delete(User user);

    String getUserType(String id);

    String getPassword(String id);

    boolean changeState(String id);

}
