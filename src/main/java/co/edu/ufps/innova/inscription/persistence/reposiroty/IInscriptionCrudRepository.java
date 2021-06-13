package co.edu.ufps.innova.inscription.persistence.reposiroty;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.inscription.persistence.entity.InscriptionEntity;
import co.edu.ufps.innova.inscription.persistence.entity.InscriptionEntityPK;

public interface IInscriptionCrudRepository extends CrudRepository<InscriptionEntity, InscriptionEntityPK> {

    /**
     * Method for find all Inscriptions by event id
     *
     * @param eventId of Event
     * @return the Event with the given event id
     */
    Optional<List<InscriptionEntity>> findByIdEventId(long eventId);

    /**
     * Method for find all Inscriptions by user id
     *
     * @param userId of User
     * @return the Event with the given user id
     */
    Optional<List<InscriptionEntity>> findByIdUserId(String userId);

}
