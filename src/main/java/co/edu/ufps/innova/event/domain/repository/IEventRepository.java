package co.edu.ufps.innova.event.domain.repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import co.edu.ufps.innova.event.domain.dto.Event;
import co.edu.ufps.innova.event.domain.dto.EventType;
import co.edu.ufps.innova.event.domain.dto.EventState;

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
     * Method for list all Events by type
     *
     * @param type of Event
     * @return all Events with the given type
     */
    Optional<List<Event>> findByType(EventType type);

    /**
     * Method for list all Events by state
     *
     * @param state of Event
     * @return all Events with the given state
     */
    Optional<List<Event>> findByState(EventState state);

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
     * @return all Events with registration dead line date after a date
     */
    Optional<List<Event>> findByRegistrationDeadlineDateAfter(LocalDate date);

    /**
     * Method for delete an Event
     *
     * @param event to delete
     */
    void delete(Event event);

}
