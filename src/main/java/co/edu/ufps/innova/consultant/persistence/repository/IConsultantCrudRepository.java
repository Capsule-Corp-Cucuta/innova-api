package co.edu.ufps.innova.consultant.persistence.repository;

import java.util.Set;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.consultant.persistence.entity.ConsultantEntity;

public interface IConsultantCrudRepository extends CrudRepository<ConsultantEntity, String> {

    /**
     * Method for get a list of active Consultants
     *
     * @return all active Consultants
     */
    Optional<Set<ConsultantEntity>> findByActive(boolean active);

}
