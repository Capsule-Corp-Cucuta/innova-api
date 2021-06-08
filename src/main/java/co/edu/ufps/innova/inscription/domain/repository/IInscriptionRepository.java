package co.edu.ufps.innova.inscription.domain.repository;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.inscription.domain.dto.Inscription;

public interface IInscriptionRepository {

    Inscription save(Inscription inscription);

    void update(List<Inscription> inscriptions);

    Optional<Inscription> findById(long eventId, String contactId);

    Optional<List<Inscription>> findByEventId(long eventId);

    void delete(Inscription inscription);

}
