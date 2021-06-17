package co.edu.ufps.innova.user.persistence.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.user.persistence.entity.UserEntity;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
public interface IUserCrudRepository extends CrudRepository<UserEntity, String> {

    /**
     * Method for find an User by email
     *
     * @param email of the User
     * @return User with the given email or Optional
     */
    Optional<UserEntity> findByEmail(String email);

}
