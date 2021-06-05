package co.edu.ufps.innova.consultant.persistence.repository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import co.edu.ufps.innova.consultant.domain.dto.Consultant;
import co.edu.ufps.innova.consultant.persistence.entity.ConsultantEntity;
import co.edu.ufps.innova.consultant.persistence.mapper.IConsultantMapper;
import co.edu.ufps.innova.consultant.domain.repository.IConsultantRepository;

@Repository
@RequiredArgsConstructor
public class ConsultantRepositoryImpl implements IConsultantRepository {

    private final IConsultantMapper mapper;
    private final IConsultantCrudRepository repository;

    @Override
    public Consultant save(Consultant consultant, String password) {
        ConsultantEntity entity = mapper.toConsultantEntity(consultant);
        entity.setPassword(password);
        entity.setActive(true);
        return mapper.toConsultant(repository.save(entity));
    }

    @Override
    public Optional<Consultant> findById(String id) {
        return repository.findById(id).map(mapper::toConsultant);
    }

    @Override
    public Optional<Consultant> findByCode(String code) {
        return repository.findByCode(code).map(mapper::toConsultant);
    }

    @Override
    public List<Consultant> findAll() {
        return mapper.toConsultantList((List<ConsultantEntity>) repository.findAll());
    }

    @Override
    public Optional<List<Consultant>> findByActive() {
        return repository.findByActive(true).map(mapper::toConsultantList);
    }

    @Override
    public void delete(Consultant consultant) {
        repository.delete(mapper.toConsultantEntity(consultant));
    }

}
