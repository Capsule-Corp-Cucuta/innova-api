package co.edu.ufps.innova.inscription.persistence.reposiroty;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import co.edu.ufps.innova.inscription.domain.dto.Inscription;
import co.edu.ufps.innova.inscription.persistence.mapper.IInscriptionMapper;
import co.edu.ufps.innova.inscription.persistence.entity.InscriptionEntityPK;
import co.edu.ufps.innova.inscription.domain.repository.IInscriptionRepository;

@Repository
@RequiredArgsConstructor
public class InscriptionRepository implements IInscriptionRepository {

    private final IInscriptionMapper mapper;
    private final IInscriptionCrudRepository repository;

    @Override
    public Inscription save(Inscription inscription) {
        return mapper.toInscription(repository.save(mapper.toInscriptionEntity(inscription)));
    }

    @Override
    public void update(List<Inscription> inscriptions) {
        repository.saveAll(mapper.toInscriptionEntities(inscriptions));
    }

    @Override
    public Optional<Inscription> findById(long eventId, String userId) {
        return repository.findById(new InscriptionEntityPK(eventId, userId)).map(mapper::toInscription);
    }

    @Override
    public Optional<List<Inscription>> findByEventId(long eventId) {
        return repository.findByEventId(eventId).map(mapper::toInscriptions);
    }

    @Override
    public void delete(Inscription inscription) {
        repository.delete(mapper.toInscriptionEntity(inscription));
    }
}
