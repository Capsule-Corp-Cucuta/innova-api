package co.edu.ufps.innova.inscription.domain.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.inscription.domain.dto.Inscription;
import co.edu.ufps.innova.inscription.domain.repository.IInscriptionRepository;

@Service
@RequiredArgsConstructor
public class InscriptionService {

    private final IInscriptionRepository repository;

    public Inscription save(Inscription inscription) {
        inscription.setInscriptionDate(LocalDate.now());
        return repository.save(inscription);
    }

    public boolean update(long eventId, String contactId, Inscription inscription) {
        return findById(eventId, contactId)
                .map(item -> {
                    inscription.setEventId(item.getEventId());
                    inscription.setUserId(item.getUserId());
                    save(inscription);
                    return true;
                })
                .orElse(false);
    }

    public void update(List<Inscription> inscriptions) {
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

}
