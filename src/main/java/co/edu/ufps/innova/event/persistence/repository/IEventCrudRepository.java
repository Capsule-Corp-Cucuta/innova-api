package co.edu.ufps.innova.event.persistence.repository;

import java.util.Set;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.event.persistence.entity.EventEntity;

public interface IEventCrudRepository extends CrudRepository<EventEntity, Long> {

    /**
     * Method for list all Events between two dates
     *
     * @param startDate first date
     * @param endDate   second date
     * @return all Events between the given dates
     */
    Optional<Set<EventEntity>> findByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Method for list all Events with registration dead line date after a date
     *
     * @param registrationDeadlineDate date to find
     * @return all Events after the given date
     */
    Optional<Set<EventEntity>> findByRegistrationDeadlineDateGreaterThanEqual(LocalDate registrationDeadlineDate);

}
