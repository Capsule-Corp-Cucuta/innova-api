package co.edu.ufps.innova.consultant.persistence.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.consultant.persistence.entity.ConsultantEntity;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
public interface IConsultantCrudRepository extends CrudRepository<ConsultantEntity, String> {

    /**
     * Method for get a list of active Consultants
     *
     * @param active state of the Consultant
     * @return all active Consultants
     */
    Optional<List<ConsultantEntity>> findByActive(boolean active);

}
