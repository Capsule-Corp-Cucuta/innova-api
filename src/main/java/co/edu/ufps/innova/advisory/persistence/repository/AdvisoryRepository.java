package co.edu.ufps.innova.advisory.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import co.edu.ufps.innova.advisory.domain.dto.Advisory;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryArea;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryType;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryState;
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
    public List<Advisory> findAll() {
        return mapper.toAdvisories((List<AdvisoryEntity>) repository.findAll());
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
    public Optional<List<Advisory>> findByConsultant(String consultantId) {
        return repository.findByConsultantId(consultantId).map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Advisory>> findByClient(String clientId) {
        return repository.findByClientId(clientId).map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Advisory>> findByType(AdvisoryType type) {
        return repository.findByType(type).map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Advisory>> findByArea(AdvisoryArea area) {
        return repository.findByArea(area).map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Advisory>> findByState(AdvisoryState state) {
        return repository.findByState(state).map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Advisory>> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByDateBetween(startDate, endDate).map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Advisory>> findByConsultantAndClient(String consultantId, String clientId) {
        return repository.findByConsultantIdAndClientId(consultantId, clientId).map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Advisory>> findByConsultantAndType(String consultantId, AdvisoryType type) {
        return repository.findByConsultantIdAndType(consultantId, type).map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Advisory>> findByConsultantAndArea(String consultantId, AdvisoryArea area) {
        return repository.findByConsultantIdAndArea(consultantId, area).map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Advisory>> findByConsultantAndState(String consultantId, AdvisoryState state) {
        return repository.findByConsultantIdAndState(consultantId, state).map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Advisory>> findByConsultantAndBetweenDates(String consultantId, LocalDateTime startDate,
                                                                    LocalDateTime endDate) {
        return repository.findByConsultantIdAndDateBetween(consultantId, startDate, endDate).map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Advisory>> findByConsultantAndClientBetweenDates(String consultantId, String clientId,
                                                                          LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByConsultantIdAndClientIdAndDateBetween(consultantId, clientId, startDate, endDate)
                .map(mapper::toAdvisories);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long count() {
        return repository.count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countByConsultant(String consultantId) {
        return repository.countByConsultantId(consultantId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countByConsultantBetweenDates(String consultantId, LocalDateTime startDate, LocalDateTime endDate) {
        return repository.countByConsultantIdAndDateBetween(consultantId, startDate, endDate);
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Advisory advisory) {
        repository.delete(mapper.toAdvisoryEntity(advisory));
    }
}
