package co.edu.ufps.innova.event.domain.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.event.domain.dto.Event;
import co.edu.ufps.innova.event.domain.dto.EventType;
import co.edu.ufps.innova.event.domain.dto.EventState;
import co.edu.ufps.innova.event.domain.repository.IEventRepository;

@Service
@RequiredArgsConstructor
public class EventService {

    private final IEventRepository repository;

    public Event save(Event event) {
        return repository.save(event);
    }

    public boolean update(long id, Event event) {
        return findById(id).map(item -> {
            event.setId(item.getId());
            repository.save(event);
            return true;
        }).orElse(false);
    }

    public List<Event> findAll() {
        return repository.findAll();
    }

    public Optional<Event> findById(long id) {
        return repository.findById(id);
    }

    public Optional<List<Event>> findByType(EventType type) {
        return repository.findByType(type);
    }

    public Optional<List<Event>> findByState(EventState state) {
        return repository.findByState(state);
    }

    public Optional<List<Event>> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findBetweenDates(startDate, endDate);
    }

    public boolean delete(long id) {
        return findById(id).map(event -> {
            repository.delete(event);
            return true;
        }).orElse(false);
    }

}
