package co.edu.ufps.innova.security.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.security.persistence.entity.UserEntity;

public interface IUserCrudRepository extends CrudRepository<UserEntity, String> {
}
