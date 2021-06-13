package co.edu.ufps.innova.inscription.domain.repository;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.inscription.domain.dto.Inscription;

public interface IInscriptionRepository {

    /**
     * Method for persist an Event
     *
     * @param inscription to persist
     * @return the saved Inscription
     */
    Inscription save(Inscription inscription);

    /**
     * Method for update a list of Inscriptions
     *
     * @param inscriptions to update
     */
    void update(List<Inscription> inscriptions);

    /**
     * Method for find an Inscription by id
     *
     * @param eventId   of event
     * @param contactId of contact
     * @return the Inscription with the given id
     */
    Optional<Inscription> findById(long eventId, String contactId);

    /**
     * Method for delete an Inscription
     *
     * @param inscription
     */
    void delete(Inscription inscription);

    void deleteAll(List<Inscription> inscriptions);

    /**
     * Method for find an Inscription by event id
     *
     * @param eventId of event
     * @return all Inscriptions with the given event id
     */
    Optional<List<Inscription>> findByEventId(long eventId);

    Optional<List<Inscription>> findByUserId(String userId);

}
