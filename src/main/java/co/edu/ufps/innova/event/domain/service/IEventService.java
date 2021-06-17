package co.edu.ufps.innova.event.domain.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import co.edu.ufps.innova.event.domain.dto.Event;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
public interface IEventService {

    /**
     * Method for persist an Event
     *
     * @param event to persist
     * @return the saved Event
     */
    Event save(Event event);

    /**
     * Method for update an Event
     *
     * @param id    of Event
     * @param event to update
     * @return true if the Event was updated
     */
    boolean update(long id, Event event);

    /**
     * Method for get all Events
     *
     * @return all Events
     */
    List<Event> findAll();

    /**
     * Method for find an Event by id
     *
     * @param id of Event
     * @return the Event with the given id
     */
    Optional<Event> findById(long id);

    /**
     * Method for delete an Event
     *
     * @param id of Event
     * @return true if the Event was deleted
     */
    boolean delete(long id);

    /**
     * Method for get all Events between two dates
     *
     * @param startDate first date
     * @param endDate   second date
     * @return all Events between two dates
     */
    Optional<List<Event>> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Method for get all Events with the registration dead line date after now
     *
     * @return all Events with the registration dead line date after now
     */
    Optional<List<Event>> findByRegistrationDeadlineDateAfterNow();

}
