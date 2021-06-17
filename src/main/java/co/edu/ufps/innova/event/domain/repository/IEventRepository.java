package co.edu.ufps.innova.event.domain.repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import co.edu.ufps.innova.event.domain.dto.Event;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
public interface IEventRepository {

    /**
     * Method for persist an Event
     *
     * @param event to save
     * @return the saved Event
     */
    Event save(Event event);

    /**
     * Method for list all Events
     *
     * @return all Events
     */
    List<Event> findAll();

    /**
     * Method for find an Event by id
     *
     * @param id of Event
     * @return Event with the given id
     */
    Optional<Event> findById(long id);

    /**
     * Method for delete an Event
     *
     * @param event to delete
     */
    void delete(Event event);

    /**
     * Method for list all Events between two dates
     *
     * @param startDate first date
     * @param endDate   second date
     * @return all Events between the given dates
     */
    Optional<List<Event>> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Method for list all Events with registration dead line date after a date
     *
     * @param date could be now
     * @return all Events with registration dead line date after a date
     */
    Optional<List<Event>> findByRegistrationDeadlineDateAfter(LocalDate date);

}
