package co.edu.ufps.innova.inscription.domain.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.event.domain.dto.EventState;
import co.edu.ufps.innova.event.domain.service.IEventService;
import co.edu.ufps.innova.inscription.domain.dto.Inscription;
import co.edu.ufps.innova.inscription.domain.repository.IInscriptionRepository;

@Service
@RequiredArgsConstructor
public class InscriptionService implements IInscriptionService {

    private final IEventService eventService;
    private final IInscriptionRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Inscription save(Inscription inscription) {
        LocalDate now = LocalDate.now();
        return eventService.findById(inscription.getEventId()).map(event -> {
            if ((now.isBefore(event.getRegistrationDeadlineDate()) || now.isEqual(event.getRegistrationDeadlineDate())) &&
                    (event.getState() == EventState.ABIERTO || event.getState() == EventState.POSPUESTO)
            ) {
                inscription.setInscriptionDate(now);
                return repository.save(inscription);
            }
            return null;
        }).orElse(null);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(List<Inscription> inscriptions) {
        repository.update(inscriptions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Inscription> findById(long eventId, String userId) {
        return repository.findById(eventId, userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(long eventId, String userId) {
        return findById(eventId, userId)
                .map(item -> {
                    repository.delete(item);
                    return true;
                })
                .orElse(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll(List<Inscription> inscriptions) {
        repository.deleteAll(inscriptions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Inscription>> findByEventId(long eventId) {
        return repository.findByEventId(eventId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Inscription>> findByUserId(String userId) {
        return repository.findByUserId(userId);
    }

}
