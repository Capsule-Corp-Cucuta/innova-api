package co.edu.ufps.innova.advisory.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryArea;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryType;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryState;
import co.edu.ufps.innova.advisory.persistence.entity.AdvisoryEntity;

public interface IAdvisoryCrudRepository extends CrudRepository<AdvisoryEntity, Long> {

    /**
     * Method for get all Advisories by consultant id
     *
     * @param consultantId consultant id
     * @return all Advisories with the given consultant id
     */
    Optional<List<AdvisoryEntity>> findByConsultantId(String consultantId);

    /**
     * Method for get all Advisories by client id
     *
     * @param clientId client id
     * @return all Advisories with the given client id
     */
    Optional<List<AdvisoryEntity>> findByClientId(String clientId);

    /**
     * Method for get all Advisories by type
     *
     * @param type of Advisory
     * @return all Advisories with the given type
     */
    Optional<List<AdvisoryEntity>> findByType(AdvisoryType type);

    /**
     * Method for get all advisories by area
     *
     * @param area of Advisory
     * @return all advisories with the given area
     */
    Optional<List<AdvisoryEntity>> findByArea(AdvisoryArea area);

    /**
     * Method for get all Advisories by state
     *
     * @param state of Advisory
     * @return all Advisories with the given state
     */
    Optional<List<AdvisoryEntity>> findByState(AdvisoryState state);

    /**
     * Method for get all Advisories between two dates
     *
     * @param startDate first date
     * @param endDate   second date
     * @return all Advisories between two dates
     */
    Optional<List<AdvisoryEntity>> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Method for get all Advisories by consultant id and client id
     *
     * @param consultantId consultant id
     * @param clientId     client id
     * @return all Advisories with the given consultant id and client id
     */
    Optional<List<AdvisoryEntity>> findByConsultantIdAndClientId(String consultantId, String clientId);

    /**
     * Method for get all Advisories by consultant id and advisory type
     *
     * @param consultantId consultant id
     * @param type         of Advisory
     * @return all Advisories with the given consultant id and advisory type
     */
    Optional<List<AdvisoryEntity>> findByConsultantIdAndType(String consultantId, AdvisoryType type);

    /**
     * Method for get all Advisories by consultant id and advisory area
     *
     * @param consultantId consultant id
     * @param area         of Advisory
     * @return all Advisories with the given consultant id and advisory area
     */
    Optional<List<AdvisoryEntity>> findByConsultantIdAndArea(String consultantId, AdvisoryArea area);

    /**
     * Method for get all Advisories by consultant id and advisory state
     *
     * @param consultantId consultant id
     * @param state        of Advisory
     * @return all Advisories with the given consultant id and advisory state
     */
    Optional<List<AdvisoryEntity>> findByConsultantIdAndState(String consultantId, AdvisoryState state);

    /**
     * Method for get all Advisories by consultant id and between two dates
     *
     * @param consultantId consultant id
     * @param startDate    first date
     * @param endDate      second date
     * @return all Advisories with the given consultant id and two dates
     */
    Optional<List<AdvisoryEntity>> findByConsultantIdAndDateBetween(String consultantId, LocalDateTime startDate,
                                                                    LocalDateTime endDate);

    /**
     * Method for get all Advisories by consultant id and client id and between two dates
     *
     * @param consultantId consultant id
     * @param clientId     client id
     * @param startDate    first date
     * @param endDate      second date
     * @return all Advisories with the given consultant id and client id and two dates
     */
    Optional<List<AdvisoryEntity>> findByConsultantIdAndClientIdAndDateBetween(String consultantId, String clientId,
                                                                               LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Method for count all Advisories by consultant id
     *
     * @param consultantId consultant id
     * @return number of all Advisories with the given consultant id
     */
    Long countByConsultantId(String consultantId);

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
