package co.edu.ufps.innova.consultant.persistence.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.consultant.persistence.entity.ConsultantEntity;

public interface IConsultantCrudRepository extends CrudRepository<ConsultantEntity, String> {

    /**
     * Method for find a Consultant by Code
     *
     * @param code of the Consultant
     * @return Consultant with the given code or Optional
     */
    Optional<ConsultantEntity> findByCode(String code);

    /**
     * Method for get a list of active Consultants
     *
     * @return all active Consultants
     */
    Optional<List<ConsultantEntity>> findByActive(boolean active);

}
