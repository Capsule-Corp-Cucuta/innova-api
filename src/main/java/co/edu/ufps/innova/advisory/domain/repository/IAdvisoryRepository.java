package co.edu.ufps.innova.advisory.domain.repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import co.edu.ufps.innova.advisory.domain.dto.Advisory;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryArea;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryType;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryState;

public interface IAdvisoryRepository {

    /**
     * Method for persist an Advisory
     *
     * @param advisory to save
     * @return the saved Advisory
     */
    Advisory save(Advisory advisory);

    /**
     * Method for get all Advisories
     *
     * @return all Advisories
     */
    List<Advisory> findAll();

    /**
     * Method for find an Advisory by id
     *
     * @return Advisory with the given id
     */
    Optional<Advisory> findById(long id);

    /**
     * Method for get all Advisories by consultant id
     *
     * @param consultantId consultant id
     * @return all Advisories with the given consultant id
     */
    Optional<List<Advisory>> findByConsultant(String consultantId);

    /**
     * Method for get all Advisories by client id
     *
     * @param clientId client id
     * @return all Advisories with the given client id
     */
    Optional<List<Advisory>> findByClient(String clientId);

    /**
     * Method for get all Advisories by type
     *
     * @param type of Advisory
     * @return all Advisories with the given type
     */
    Optional<List<Advisory>> findByType(AdvisoryType type);

    /**
     * Method for get all advisories by area
     *
     * @param area of Advisory
     * @return all advisories with the given area
     */
    Optional<List<Advisory>> findByArea(AdvisoryArea area);

    /**
     * Method for get all Advisories by state
     *
     * @param state of Advisory
     * @return all Advisories with the given state
     */
    Optional<List<Advisory>> findByState(AdvisoryState state);

    /**
     * Method for get all Advisories between two dates
     *
     * @param startDate first date
     * @param endDate   second date
     * @return all Advisories between two dates
     */
    Optional<List<Advisory>> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Method for get all Advisories by consultant id and client id
     *
     * @param consultantId consultant id
     * @param clientId     client id
     * @return all Advisories with the given consultant id and client id
     */
    Optional<List<Advisory>> findByConsultantAndClient(String consultantId, String clientId);

    /**
     * Method for get all Advisories by consultant id and advisory type
     *
     * @param consultantId consultant id
     * @param type         of Advisory
     * @return all Advisories with the given consultant id and advisory type
     */
    Optional<List<Advisory>> findByConsultantAndType(String consultantId, AdvisoryType type);

    /**
     * Method for get all Advisories by consultant id and advisory area
     *
     * @param consultantId consultant id
     * @param area         of Advisory
     * @return all Advisories with the given consultant id and advisory area
     */
    Optional<List<Advisory>> findByConsultantAndArea(String consultantId, AdvisoryArea area);

    /**
     * Method for get all Advisories by consultant id and advisory state
     *
     * @param consultantId consultant id
     * @param state        of Advisory
     * @return all Advisories with the given consultant id and advisory state
     */
    Optional<List<Advisory>> findByConsultantAndState(String consultantId, AdvisoryState state);

    /**
     * Method for get all Advisories by consultant id and between two dates
     *
     * @param consultantId consultant id
     * @param startDate    first date
     * @param endDate      second date
     * @return all Advisories with the given consultant id and two dates
     */
    Optional<List<Advisory>> findByConsultantAndBetweenDates(String consultantId, LocalDateTime startDate,
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
    Optional<List<Advisory>> findByConsultantAndClientBetweenDates(String consultantId, String clientId,
                                                                   LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Method for count all Advisories
     *
     * @return number of all Advisories
     */
    long count();

    /**
     * Method for count all Advisories by consultant id
     *
     * @param consultantId consultant id
     * @return number of all Advisories with the given consultant id
     */
    long countByConsultant(String consultantId);

    /**
     * Method for count all Advisories by consultant and two dates
     *
     * @param consultantId consultant id
     * @param startDate    first date
     * @param endDate      second date
     * @return number of all Advisories with the given consultant id and two dates
     */
    long countByConsultantBetweenDates(String consultantId, LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Method for delete an Advisory
     *
     * @param advisory to delete
     */
    void delete(Advisory advisory);

}
