package co.edu.ufps.innova.event.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import co.edu.ufps.innova.event.domain.dto.EventType;
import co.edu.ufps.innova.event.domain.dto.EventState;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.event.persistence.entity.EventEntity;

public interface IEventCrudRepository extends CrudRepository<EventEntity, Long> {

    /**
     * Method for list all Events by type
     *
     * @param type of Event
     * @return all Events with the given type
     */
    Optional<List<EventEntity>> findByType(EventType type);

    /**
     * Method for list all Events by state
     *
     * @param state of Event
     * @return all Events with the given state
     */
    Optional<List<EventEntity>> findByState(EventState state);

    /**
     * Method for list all Events between two dates
     *
     * @param startDate first date
     * @param endDate   second date
     * @return all Events between the given dates
     */
    Optional<List<EventEntity>> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

}
