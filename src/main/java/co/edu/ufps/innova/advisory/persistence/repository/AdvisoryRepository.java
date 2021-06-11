package co.edu.ufps.innova.advisory.persistence.repository;

import java.util.Set;
import java.util.Optional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import co.edu.ufps.innova.advisory.domain.dto.Advisory;
import co.edu.ufps.innova.advisory.persistence.entity.AdvisoryEntity;
import co.edu.ufps.innova.advisory.persistence.mapper.IAdvisoryMapper;
import co.edu.ufps.innova.advisory.domain.repository.IAdvisoryRepository;

@Repository
@RequiredArgsConstructor
public class AdvisoryRepository implements IAdvisoryRepository {

    private final IAdvisoryMapper mapper;
    private final IAdvisoryCrudRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Advisory save(Advisory advisory) {
        return mapper.toAdvisory(repository.save(mapper.toAdvisoryEntity(advisory)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Advisory> findAll() {
        return mapper.toAdvisories((Set<AdvisoryEntity>) repository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Advisory> findById(long id) {
        return repository.findById(id).map(mapper::toAdvisory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Advisory advisory) {
        repository.delete(mapper.toAdvisoryEntity(advisory));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Set<Advisory>> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByDateBetween(startDate, endDate).map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Set<Advisory>> findByConsultant(String consultantId) {
        return repository.findByConsultantId(consultantId).map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Set<Advisory>> findByConsultantAndBetweenDates(String consultantId,
                                                                   LocalDateTime startDate,
                                                                   LocalDateTime endDate) {
        return repository.findByConsultantIdAndDateBetween(consultantId, startDate, endDate).map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Set<Advisory>> findByClient(String clientId) {
        return repository.findByClientId(clientId).map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Set<Advisory>> findByConsultantAndClient(String consultantId, String clientId) {
        return repository.findByConsultantIdAndClientId(consultantId, clientId).map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Set<Advisory>> findByConsultantAndClientBetweenDates(String consultantId,
                                                                         String clientId,
                                                                         LocalDateTime startDate,
                                                                         LocalDateTime endDate) {
        return repository.findByConsultantIdAndClientIdAndDateBetween(consultantId, clientId, startDate, endDate)
                .map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countByConsultantBetweenDates(String consultantId, LocalDateTime startDate, LocalDateTime endDate) {
        return repository.countByConsultantIdAndDateBetween(consultantId, startDate, endDate);
    }

}
