package co.edu.ufps.innova.event.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import co.edu.ufps.innova.event.domain.dto.Event;
import co.edu.ufps.innova.event.domain.dto.EventType;
import co.edu.ufps.innova.event.domain.dto.EventState;
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
    public List<Event> findAll() {
        return mapper.toEvents((List<EventEntity>) repository.findAll());
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
    public Optional<List<Event>> findByType(EventType type) {
        return repository.findByType(type).map(mapper::toEvents);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Event>> findByState(EventState state) {
        return repository.findByState(state).map(mapper::toEvents);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Event>> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByStartDateBetween(startDate, endDate).map(mapper::toEvents);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Event event) {
        repository.delete(mapper.toEventEntity(event));
    }
}
