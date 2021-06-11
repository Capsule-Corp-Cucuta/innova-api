package co.edu.ufps.innova.advisory.persistence.repository;

import java.util.Set;
import java.util.Optional;
import java.time.LocalDateTime;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.advisory.persistence.entity.AdvisoryEntity;

public interface IAdvisoryCrudRepository extends CrudRepository<AdvisoryEntity, Long> {

    /**
     * Method for get all Advisories between two dates
     *
     * @param startDate first date
     * @param endDate   second date
     * @return all Advisories between two dates
     */
    Optional<Set<AdvisoryEntity>> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Method for get all Advisories by consultant id
     *
     * @param consultantId consultant id
     * @return all Advisories with the given consultant id
     */
    Optional<Set<AdvisoryEntity>> findByConsultantId(String consultantId);

    /**
     * Method for get all Advisories by consultant id and between two dates
     *
     * @param consultantId consultant id
     * @param startDate    first date
     * @param endDate      second date
     * @return all Advisories with the given consultant id and two dates
     */
    Optional<Set<AdvisoryEntity>> findByConsultantIdAndDateBetween(String consultantId,
                                                                   LocalDateTime startDate,
                                                                   LocalDateTime endDate);

    /**
     * Method for get all Advisories by client id
     *
     * @param clientId client id
     * @return all Advisories with the given client id
     */
    Optional<Set<AdvisoryEntity>> findByClientId(String clientId);

    /**
     * Method for get all Advisories by consultant id and client id
     *
     * @param consultantId consultant id
     * @param clientId     client id
     * @return all Advisories with the given consultant id and client id
     */
    Optional<Set<AdvisoryEntity>> findByConsultantIdAndClientId(String consultantId, String clientId);

    /**
     * Method for get all Advisories by consultant id and client id and between two dates
     *
     * @param consultantId consultant id
     * @param clientId     client id
     * @param startDate    first date
     * @param endDate      second date
     * @return all Advisories with the given consultant id and client id and two dates
     */
    Optional<Set<AdvisoryEntity>> findByConsultantIdAndClientIdAndDateBetween(String consultantId, String clientId,
                                                                              LocalDateTime startDate,
                                                                              LocalDateTime endDate);

    /**
     * Method for count all Advisories by consultant and two dates
     *
     * @param consultantId consultant id
     * @param startDate    first date
     * @param endDate      second date
     * @return number of all Advisories with the given consultant id and two dates
     */
    Long countByConsultantIdAndDateBetween(String consultantId, LocalDateTime startDate, LocalDateTime endDate);

}
