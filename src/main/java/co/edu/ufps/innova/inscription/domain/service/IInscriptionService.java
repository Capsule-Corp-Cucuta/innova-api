package co.edu.ufps.innova.inscription.domain.service;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.inscription.domain.dto.Inscription;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
public interface IInscriptionService {

    /**
     * Method for persist an Inscription
     *
     * @param inscription to persist
     * @return the saved Inscription
     */
    Inscription save(Inscription inscription);

    /**
     * Method for persist a list of Inscriptions
     *
     * @param inscriptions to persist
     */
    void saveAll(List<Inscription> inscriptions);

    /**
     * Method for find an Inscription by id
     *
     * @param eventId of Event
     * @param userId  of User
     * @return the Inscription with the given id
     */
    Optional<Inscription> findById(long eventId, String userId);

    /**
     * Method for delete an Inscription
     *
     * @param eventId of Event
     * @param userId  of User
     * @return true if the Inscription was deleted
     */
    boolean delete(long eventId, String userId);

    /**
     * Method for delete a List of Inscriptions
     *
     * @param inscriptions to delete
     */
    void deleteAll(List<Inscription> inscriptions);

    /**
     * Method for get all Inscriptions by Event id
     *
     * @param eventId of Event
     * @return all Inscriptions with the given Event id
     */
    Optional<List<Inscription>> findByEventId(long eventId);

    /**
     * Method for get all Inscriptions by User id
     *
     * @param userId of User
     * @return all Inscriptions with the given User id
     */
    Optional<List<Inscription>> findByUserId(String userId);

}
