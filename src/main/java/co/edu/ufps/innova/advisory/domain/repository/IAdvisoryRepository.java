package co.edu.ufps.innova.advisory.domain.repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import co.edu.ufps.innova.advisory.domain.dto.Advisory;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
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
     * @param id of the Advisory
     * @return the Advisory with the given id
     */
    Optional<Advisory> findById(long id);

    /**
     * Method for delete an Advisory
     *
     * @param advisory to delete
     */
    void delete(Advisory advisory);

    /**
     * Method for get all Advisories between two dates
     *
     * @param startDate first date
     * @param endDate   second date
     * @return all Advisories between two dates
     */
    Optional<List<Advisory>> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

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
     * Method for get all Advisories by consultant id and client id
     *
     * @param consultantId consultant id
     * @param clientId     client id
     * @return all Advisories with the given consultant id and client id
     */
    Optional<List<Advisory>> findByConsultantAndClient(String consultantId, String clientId);

    /**
     * Method for get all Advisories by consultant id and between two dates
     *
     * @param consultantId consultant id
     * @param startDate    first date
     * @param endDate      second date
     * @return all Advisories with the given consultant id and two dates
     */
    Optional<List<Advisory>> findByConsultantAndBetweenDates(String consultantId,
                                                            LocalDateTime startDate,
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
    Optional<List<Advisory>> findByConsultantAndClientBetweenDates(String consultantId,
                                                                  String clientId,
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
    long countByConsultantBetweenDates(String consultantId, LocalDateTime startDate, LocalDateTime endDate);


}
