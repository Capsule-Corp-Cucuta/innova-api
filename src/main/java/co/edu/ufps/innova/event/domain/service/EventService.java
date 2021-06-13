package co.edu.ufps.innova.event.domain.service;

import java.util.List;
import java.util.Optional;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.event.domain.dto.Event;
import co.edu.ufps.innova.event.domain.dto.EventState;
import co.edu.ufps.innova.event.domain.repository.IEventRepository;

@Service
@RequiredArgsConstructor
public class EventService {

    private final IEventRepository repository;

    public Event save(Event event) {
        LocalDateTime registrationDeadLineDateTime = LocalDateTime.of(event.getRegistrationDeadlineDate(), LocalTime.MAX);
        byte duration = (byte) Duration.between(event.getStartDate(), event.getCloseDate()).toHours();
        boolean canCreate = (LocalDateTime.now().isBefore(registrationDeadLineDateTime) ||
                LocalDateTime.now().isEqual(registrationDeadLineDateTime)) &&
                (event.getStartDate().isAfter(registrationDeadLineDateTime) ||
                        event.getStartDate().isEqual(registrationDeadLineDateTime)) &&
                duration > 0 && duration <= 8;
        if (canCreate) {
            event.setEventDurationInHours(duration);
            event.setState(EventState.ABIERTO);
            return repository.save(event);
        } else {
            return null;
        }
    }

    public boolean update(long id, Event event) {
        byte duration = (byte) Duration.between(event.getStartDate(), event.getCloseDate()).toHours();
        boolean canUpdate = duration > 0 && duration <= 8 &&
                event.getStartDate().isAfter(LocalDateTime.of(event.getRegistrationDeadlineDate(), LocalTime.MIN));
        return canUpdate && findById(id).map(item -> {
            if (!item.getState().equals(EventState.COMPLETADO)) {
                event.setId(item.getId());
                event.setEventDurationInHours(duration);
                repository.save(event);
                return true;
            }
            return false;
        }).orElse(false);
    }

    public List<Event> findAll() {
        return repository.findAll();
    }

    public Optional<Event> findById(long id) {
        return repository.findById(id);
    }

    public boolean delete(long id) {
        return findById(id).map(event -> {
            repository.delete(event);
            return true;
        }).orElse(false);
    }

    public Optional<List<Event>> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findBetweenDates(startDate, endDate);
    }

    public Optional<List<Event>> findByRegistrationDeadlineDateAfterNow() {
        return repository.findByRegistrationDeadlineDateAfter(LocalDate.now());
    }

}
