package co.edu.ufps.innova.event.persistence.repository;

import java.util.Set;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import co.edu.ufps.innova.event.domain.dto.Event;
import co.edu.ufps.innova.event.persistence.entity.EventEntity;
import co.edu.ufps.innova.event.persistence.mapper.IEventMapper;
import co.edu.ufps.innova.event.domain.repository.IEventRepository;

@Repository
@RequiredArgsConstructor
public class EventRepository implements IEventRepository {

    private final IEventMapper mapper;
    private final IEventCrudRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Event save(Event event) {
        return mapper.toEvent(repository.save(mapper.toEventEntity(event)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Event> findAll() {
        return mapper.toEvents((Set<EventEntity>) repository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Event> findById(long id) {
        return repository.findById(id).map(mapper::toEvent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Event event) {
        repository.delete(mapper.toEventEntity(event));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Set<Event>> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByStartDateBetween(startDate, endDate).map(mapper::toEvents);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Set<Event>> findByRegistrationDeadlineDateAfter(LocalDate date) {
        return repository.findByRegistrationDeadlineDateGreaterThanEqual(date).map(mapper::toEvents);
    }


}
