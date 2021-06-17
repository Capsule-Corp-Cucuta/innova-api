package co.edu.ufps.innova.advisory.domain.service;

import java.util.List;
import java.time.LocalDateTime;

import co.edu.ufps.innova.advisory.domain.dto.AdvisoryReport;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
public interface IAdvisoryReportService {

    /**
     * Method for list all Consultants with his hours of Advisories
     *
     * @return all Consultants with his hours of Advisories
     */
    List<AdvisoryReport> getGeneralReport();

    /**
     * Method for list all Consultants with his hours of Advisories between two dates
     *
     * @param startDate first date
     * @param endDate   second date
     * @return all Consultants with his hours of Advisories between two dates
     */
    List<AdvisoryReport> getGeneralReportBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Method for count all hours of Advisories has a Consultant
     *
     * @param consultantId of Consultant
     * @return all hours of Advisories has a Consultant
     */
    long countHoursByConsultant(String consultantId);

    /**
     * Method for count all hours of Advisories has a Consultant between dates
     *
     * @param consultantId of Consultant
     * @param startDate    first date
     * @param endDate      second date
     * @return all hours of Advisories has a Consultant between dates
     */
    long countHoursByConsultantBetweenDates(String consultantId, LocalDateTime startDate,
                                            LocalDateTime endDate);

    /**
     * Method for list all Consultants with his hours of Advisories with preparation time
     *
     * @return all Consultants with his hours of Advisories with preparation time
     */
    List<AdvisoryReport> getGeneralReportWithPreparationTime();

    /**
     * Method for list all Consultants with his hours of Advisories with preparation time between dates
     *
     * @param startDate first date
     * @param endDate   second date
     * @return all Consultants with his hours of Advisories with preparation time between dates
     */
    List<AdvisoryReport> getGeneralReportWithPreparationTimeBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Method for count all hours of Advisories with preparation time has a Consultant
     *
     * @param consultantId of Consultant
     * @return all hours of Advisories with preparation time has a Consultant
     */
    long countHoursByConsultantWithPreparationTime(String consultantId);

    /**
     * Method for count all all hours of Advisories with preparation time has a Consultant between dates
     *
     * @param consultantId of Consultant
     * @param startDate    first date
     * @param endDate      second date
     * @return all hours of Advisories with preparation time has a Consultant between dates
     */
    long countHoursByConsultantWithPreparationTimeBetweenDates(String consultantId, LocalDateTime startDate,
                                                               LocalDateTime endDate);

    /**
     * Method for count all Advisories has a Consultant between two dates
     *
     * @param consultantId of Consultant
     * @param startDate    first date
     * @param endDate      second date
     * @return all Advisories has a Consultant between two dates
     */
    long countAdvisoriesByConsultantBetweenDates(String consultantId, LocalDateTime startDate, LocalDateTime endDate);

}
