package co.edu.ufps.innova.inscription.domain.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.event.domain.dto.EventState;
import co.edu.ufps.innova.event.domain.service.EventService;
import co.edu.ufps.innova.inscription.domain.dto.Inscription;
import co.edu.ufps.innova.inscription.domain.repository.IInscriptionRepository;

@Service
@RequiredArgsConstructor
public class InscriptionService {

    private final EventService eventService;
    private final IInscriptionRepository repository;

    public Inscription save(Inscription inscription) {
        LocalDate now = LocalDate.now();
        return eventService.findById(inscription.getEventId()).map(event -> {
            if ((now.isBefore(event.getRegistrationDeadlineDate()) || now.isEqual(event.getRegistrationDeadlineDate())) &&
                    (event.getState() == EventState.OPEN || event.getState() == EventState.POSTPONED)
            ) {
                inscription.setInscriptionDate(now);
                return repository.save(inscription);
            }
            return null;
        }).orElse(null);

    }

    public void saveAll(List<Inscription> inscriptions) {
        repository.update(inscriptions);
    }

    public Optional<Inscription> findById(long eventId, String userId) {
        return repository.findById(eventId, userId);
    }

    public boolean delete(long eventId, String userId) {
        return findById(eventId, userId)
                .map(item -> {
                    repository.delete(item);
                    return true;
                })
                .orElse(false);
    }

    public void deleteAll(List<Inscription> inscriptions) {
        repository.deleteAll(inscriptions);
    }

    public Optional<List<Inscription>> findByEventId(long eventId) {
        return repository.findByEventId(eventId);
    }

    public Optional<List<Inscription>> findByUserId(String userId) {
        return repository.findByUserId(userId);
    }

}
