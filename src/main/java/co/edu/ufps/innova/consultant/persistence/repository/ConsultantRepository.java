package co.edu.ufps.innova.consultant.persistence.repository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import co.edu.ufps.innova.consultant.domain.dto.Consultant;
import co.edu.ufps.innova.consultant.persistence.entity.ConsultantEntity;
import co.edu.ufps.innova.consultant.persistence.mapper.IConsultantMapper;
import co.edu.ufps.innova.consultant.domain.repository.IConsultantRepository;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Repository
@RequiredArgsConstructor
public class ConsultantRepository implements IConsultantRepository {

    private final IConsultantMapper mapper;
    private final IConsultantCrudRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Consultant save(Consultant consultant, String password) {
        ConsultantEntity entity = mapper.toConsultantEntity(consultant);
        entity.setActive(true);
        entity.setPassword(password);
        return mapper.toConsultant(repository.save(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Consultant> findAll() {
        return mapper.toConsultantList((List<ConsultantEntity>) repository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Consultant> findById(String id) {
        return repository.findById(id).map(mapper::toConsultant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Consultant consultant) {
        repository.delete(mapper.toConsultantEntity(consultant));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Consultant>> findByActive() {
        return repository.findByActive(true).map(mapper::toConsultantList);
    }

}
