package co.edu.ufps.innova.user.persistence.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.user.persistence.entity.UserEntity;

public interface IUserCrudRepository extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);

}
