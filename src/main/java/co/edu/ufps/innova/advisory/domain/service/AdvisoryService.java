package co.edu.ufps.innova.advisory.domain.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.advisory.domain.dto.Advisory;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryArea;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryType;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryState;
import co.edu.ufps.innova.advisory.domain.repository.IAdvisoryRepository;

@Service
@RequiredArgsConstructor
public class AdvisoryService {

    private final IAdvisoryRepository repository;

    public Advisory save(Advisory advisory) {
        return repository.save(advisory);
    }

    public boolean update(long id, Advisory advisory) {
        return findById(id).map(item -> {
            advisory.setId(item.getId());
            repository.save(advisory);
            return true;
        }).orElse(false);
    }

    public List<Advisory> findAll() {
        return repository.findAll();
    }

    public Optional<Advisory> findById(long id) {
        return repository.findById(id);
    }

    public Optional<List<Advisory>> findByConsultant(String consultantId) {
        return repository.findByConsultant(consultantId);
    }

    public Optional<List<Advisory>> findByClient(String clientId) {
        return repository.findByClient(clientId);
    }

    public Optional<List<Advisory>> findByType(AdvisoryType type) {
        return repository.findByType(type);
    }

    public Optional<List<Advisory>> findByArea(AdvisoryArea area) {
        return repository.findByArea(area);
    }

    public Optional<List<Advisory>> findByState(AdvisoryState state) {
        return repository.findByState(state);
    }

    public Optional<List<Advisory>> findByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    public Optional<List<Advisory>> findBetweenDates(LocalDate startDate, LocalDate endDate) {
        return repository.findBetweenDates(startDate, endDate);
    }

    public Optional<List<Advisory>> findByConsultantAndClient(String consultantId, String clientId) {
        return repository.findByConsultantAndClient(consultantId, clientId);
    }

    public Optional<List<Advisory>> findByConsultantAndType(String consultantId, AdvisoryType type) {
        return repository.findByConsultantAndType(consultantId, type);
    }

    public Optional<List<Advisory>> findByConsultantAndArea(String consultantId, AdvisoryArea area) {
        return repository.findByConsultantAndArea(consultantId, area);
    }

    public Optional<List<Advisory>> findByConsultantAndState(String consultantId, AdvisoryState state) {
        return repository.findByConsultantAndState(consultantId, state);
    }

    public Optional<List<Advisory>> findByConsultantAndDate(String consultantId, LocalDate date) {
        return repository.findByConsultantAndDate(consultantId, date);
    }

    public Optional<List<Advisory>> findByConsultantAndBetweenDates(String consultantId, LocalDate startDate,
                                                                    LocalDate endDate) {
        return repository.findByConsultantAndBetweenDates(consultantId, startDate, endDate);
    }

    public Optional<List<Advisory>> findByConsultantAndClientAndDate(String consultantId, String clientId,
                                                                     LocalDate date) {
        return repository.findByConsultantAndClientAndDate(consultantId, clientId, date);
    }

    public Optional<List<Advisory>> findByConsultantAndClientBetweenDates(String consultantId, String clientId,
                                                                          LocalDate startDate, LocalDate endDate) {
        return repository.findByConsultantAndClientBetweenDates(consultantId, clientId, startDate, endDate);
    }

    public long count() {
        return repository.count();
    }

    public long countByConsultant(String consultantId) {
        return repository.countByConsultant(consultantId);
    }

    public long countByConsultantBetweenDates(String consultantId, LocalDate startDate, LocalDate endDate) {
        return repository.countByConsultantBetweenDates(consultantId, startDate, endDate);
    }

    public long countHoursByConsultantWithPreparation(String consultantId) {
        return findByConsultant(consultantId)
                .map(advisories -> advisories.stream()
                        .mapToLong(advisory -> advisory.getDurationInHours()
                                + advisory.getPreparationTypeInHours()).sum()
                ).orElse(0L);
    }

    public long countHoursByConsultantWithoutPreparation(String consultantId) {
        return findByConsultant(consultantId)
                .map(advisories -> advisories.stream()
                        .mapToLong(advisory -> advisory.getDurationInHours()).sum()
                ).orElse(0L);
    }

    public long countHoursByConsultantWithoutPreparationBetweenDates(String consultantId, LocalDate startDate,
                                                                     LocalDate endDate) {
        return findByConsultantAndBetweenDates(consultantId, startDate, endDate)
                .map(advisories -> advisories.stream()
                        .mapToLong(advisory -> advisory.getDurationInHours()).sum()
                ).orElse(0L);
    }

    public boolean delete(long id) {
        return findById(id).map(advisory -> {
            repository.delete(advisory);
            return true;
        }).orElse(false);
    }

}
