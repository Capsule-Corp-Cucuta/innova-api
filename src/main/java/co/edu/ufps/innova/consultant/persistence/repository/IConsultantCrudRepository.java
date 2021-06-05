package co.edu.ufps.innova.consultant.persistence.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.consultant.persistence.entity.ConsultantEntity;

public interface IConsultantCrudRepository extends CrudRepository<ConsultantEntity, String> {

    Optional<ConsultantEntity> findByCode(String code);

    Optional<List<ConsultantEntity>> findByActive(boolean active);

}
