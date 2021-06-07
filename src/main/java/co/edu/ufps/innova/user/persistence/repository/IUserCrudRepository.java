package co.edu.ufps.innova.user.persistence.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.user.persistence.entity.UserEntity;

public interface IUserCrudRepository extends CrudRepository<UserEntity, String> {

    /**
     * Method for find an User by email
     *
     * @param email of the User
     * @return User with the given email or Optional
     */
    Optional<UserEntity> findByEmail(String email);

}
