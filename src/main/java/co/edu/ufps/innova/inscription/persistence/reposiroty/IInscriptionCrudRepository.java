package co.edu.ufps.innova.inscription.persistence.reposiroty;

import java.util.Set;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.inscription.persistence.entity.InscriptionEntity;
import co.edu.ufps.innova.inscription.persistence.entity.InscriptionEntityPK;

public interface IInscriptionCrudRepository extends CrudRepository<InscriptionEntity, InscriptionEntityPK> {

    /**
     * Method for find an Event by id
     *
     * @param eventId of Event
     * @return the Event with the given id
     */
    Optional<Set<InscriptionEntity>> findByIdEventId(long eventId);

}
