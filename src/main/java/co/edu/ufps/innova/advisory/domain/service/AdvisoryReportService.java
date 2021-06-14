package co.edu.ufps.innova.advisory.domain.service;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.advisory.domain.dto.Advisory;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryReport;
import co.edu.ufps.innova.consultant.domain.service.IConsultantService;
import co.edu.ufps.innova.advisory.domain.repository.IAdvisoryRepository;

@Service
@RequiredArgsConstructor
public class AdvisoryReportService implements IAdvisoryReportService {

    private final IAdvisoryRepository repository;
    private final IAdvisoryService advisoryService;
    private final IConsultantService consultantService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AdvisoryReport> getGeneralReport() {
        List<AdvisoryReport> advisoryReports = new ArrayList<>();
        consultantService.findAll().stream()
                .forEach(consultant -> advisoryReports
                        .add(new AdvisoryReport(consultant, countHoursByConsultant(consultant.getId()))));
        return advisoryReports;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AdvisoryReport> getGeneralReportBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        List<AdvisoryReport> advisoryReports = new ArrayList<>();
        consultantService.findAll().stream()
                .forEach(consultant -> advisoryReports
                        .add(new AdvisoryReport(
                                consultant,
                                countHoursByConsultantBetweenDates(consultant.getId(), startDate, endDate)
                        ))
                );
        return advisoryReports;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countHoursByConsultant(String consultantId) {
        return advisoryService.findByConsultant(consultantId)
                .map(advisories -> advisories.stream()
                        .mapToLong(Advisory::getDurationInHours).sum()
                ).orElse(0L);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countHoursByConsultantBetweenDates(String consultantId, LocalDateTime startDate,
                                                   LocalDateTime endDate) {
        return advisoryService.findByConsultantAndBetweenDates(consultantId, startDate, endDate)
                .map(advisories -> advisories.stream()
                        .mapToLong(Advisory::getDurationInHours).sum()
                ).orElse(0L);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AdvisoryReport> getGeneralReportWithPreparationTime() {
        List<AdvisoryReport> advisoryReports = new ArrayList<>();
        consultantService.findAll().stream()
                .forEach(consultant -> advisoryReports
                        .add(new AdvisoryReport(
                                consultant,
                                countHoursByConsultantWithPreparationTime(consultant.getId())
                        ))
                );
        return advisoryReports;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AdvisoryReport> getGeneralReportWithPreparationTimeBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        List<AdvisoryReport> advisoryReports = new ArrayList<>();
        consultantService.findAll().stream()
                .forEach(consultant -> advisoryReports
                        .add(new AdvisoryReport(
                                consultant,
                                countHoursByConsultantWithPreparationTimeBetweenDates(
                                        consultant.getId(), startDate, endDate
                                )
                        ))
                );
        return advisoryReports;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countHoursByConsultantWithPreparationTime(String consultantId) {
        return advisoryService.findByConsultant(consultantId)
                .map(advisories -> advisories.stream()
                        .mapToLong(advisory -> advisory.getDurationInHours() + advisory.getPreparationTimeInHours())
                        .sum())
                .orElse(0L);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countHoursByConsultantWithPreparationTimeBetweenDates(String consultantId, LocalDateTime startDate,
                                                                      LocalDateTime endDate) {
        return advisoryService.findByConsultantAndBetweenDates(consultantId, startDate, endDate)
                .map(advisories -> advisories.stream()
                        .mapToLong(advisory -> advisory.getDurationInHours() + advisory.getPreparationTimeInHours())
                        .sum())
                .orElse(0L);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countAdvisoriesByConsultantBetweenDates(String consultantId, LocalDateTime startDate, LocalDateTime endDate) {
        return repository.countByConsultantBetweenDates(consultantId, startDate, endDate);
    }

}
