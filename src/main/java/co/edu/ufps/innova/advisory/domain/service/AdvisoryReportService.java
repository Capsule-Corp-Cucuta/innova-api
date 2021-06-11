package co.edu.ufps.innova.advisory.domain.service;

import java.util.Set;
import java.util.HashSet;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.advisory.domain.dto.Advisory;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryReport;
import co.edu.ufps.innova.consultant.domain.service.ConsultantService;
import co.edu.ufps.innova.advisory.domain.repository.IAdvisoryRepository;

@Service
@RequiredArgsConstructor
public class AdvisoryReportService {

    private final IAdvisoryRepository repository;
    private final AdvisoryService advisoryService;
    private final ConsultantService consultantService;

    public Set<AdvisoryReport> getGeneralReport() {
        Set<AdvisoryReport> advisoryReports = new HashSet<>();
        consultantService.findAll().stream()
                .forEach(consultant -> advisoryReports
                        .add(new AdvisoryReport(consultant, countHoursByConsultant(consultant.getId()))));
        return advisoryReports;
    }

    public Set<AdvisoryReport> getGeneralReportBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        Set<AdvisoryReport> advisoryReports = new HashSet<>();
        consultantService.findAll().stream()
                .forEach(consultant -> advisoryReports
                        .add(new AdvisoryReport(
                                consultant,
                                countHoursByConsultantBetweenDates(consultant.getId(), startDate, endDate)
                        ))
                );
        return advisoryReports;
    }

    public long countHoursByConsultant(String consultantId) {
        return advisoryService.findByConsultant(consultantId)
                .map(advisories -> advisories.stream()
                        .mapToLong(Advisory::getDurationInHours).sum()
                ).orElse(0L);
    }

    public long countHoursByConsultantBetweenDates(String consultantId, LocalDateTime startDate,
                                                   LocalDateTime endDate) {
        return advisoryService.findByConsultantAndBetweenDates(consultantId, startDate, endDate)
                .map(advisories -> advisories.stream()
                        .mapToLong(Advisory::getDurationInHours).sum()
                ).orElse(0L);
    }

    public Set<AdvisoryReport> getGeneralReportWithPreparationTime() {
        Set<AdvisoryReport> advisoryReports = new HashSet<>();
        consultantService.findAll().stream()
                .forEach(consultant -> advisoryReports
                        .add(new AdvisoryReport(
                                consultant,
                                countHoursByConsultantWithPreparationTime(consultant.getId())
                        ))
                );
        return advisoryReports;
    }

    public Set<AdvisoryReport> getGeneralReportWithPreparationTimeBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        Set<AdvisoryReport> advisoryReports = new HashSet<>();
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

    public long countHoursByConsultantWithPreparationTime(String consultantId) {
        return advisoryService.findByConsultant(consultantId)
                .map(advisories -> advisories.stream()
                        .mapToLong(advisory -> advisory.getDurationInHours() + advisory.getPreparationTimeInHours())
                        .sum())
                .orElse(0L);
    }

    public long countHoursByConsultantWithPreparationTimeBetweenDates(String consultantId, LocalDateTime startDate,
                                                                      LocalDateTime endDate) {
        return advisoryService.findByConsultantAndBetweenDates(consultantId, startDate, endDate)
                .map(advisories -> advisories.stream()
                        .mapToLong(advisory -> advisory.getDurationInHours() + advisory.getPreparationTimeInHours())
                        .sum())
                .orElse(0L);
    }

    public long countAdvisoriesByConsultantBetweenDates(String consultantId, LocalDateTime startDate, LocalDateTime endDate) {
        return repository.countByConsultantBetweenDates(consultantId, startDate, endDate);
    }

}
