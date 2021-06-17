package co.edu.ufps.innova.advisory.domain.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import co.edu.ufps.innova.advisory.domain.dto.Advisory;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
public interface IAdvisoryService {

    /**
     * Method for persist an Advisory
     *
     * @param advisory to persist
     * @return the saved Advisory
     */
    Advisory save(Advisory advisory);

    /**
     * Method for update an Advisory
     *
     * @param id       of Advisory
     * @param advisory to update
     * @return true if the Advisory was updated
     */
    boolean update(long id, Advisory advisory);

    /**
     * Method for get all Advisories
     *
     * @return all Advisories
     */
    List<Advisory> findAll();

    /**
     * Method for find an Advisory by id
     *
     * @param id of Advisory
     * @return the Advisory with the given id
     */
    Optional<Advisory> findById(long id);

    /**
     * Method for delete an Advisory
     *
     * @param id of Advisory
     * @return true if the Advisory was deleted
     */
    boolean delete(long id);

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
     * @param consultantId of Consultant
     * @return all Advisories with the given Consultant id
     */
    Optional<List<Advisory>> findByConsultant(String consultantId);

    /**
     * Method for get all Advisories between two dates and Consultant id
     *
     * @param consultantId of Consultant
     * @param startDate    first date
     * @param endDate      second date
     * @return all Advisories with the given two dates and Consultant id
     */
    Optional<List<Advisory>> findByConsultantAndBetweenDates(String consultantId,
                                                             LocalDateTime startDate,
                                                             LocalDateTime endDate);

    /**
     * Method for get all advisories by Client id
     *
     * @param clientId of Client
     * @return all advisories with the given Client id
     */
    Optional<List<Advisory>> findByClient(String clientId);

    /**
     * Method for get all advisories by consultant and client
     *
     * @param consultantId of Consultant
     * @param clientId     of Client
     * @return all advisories with the given consultant id and client id
     */
    Optional<List<Advisory>> findByConsultantAndClient(String consultantId, String clientId);

    /**
     * Method for get all advisories between dates and consultant id and client id
     *
     * @param consultantId of Consultant
     * @param clientId     of Client
     * @param startDate    first date
     * @param endDate      second date
     * @return all advisories with the given dates and consultant id and client id
     */
    Optional<List<Advisory>> findByConsultantAndClientBetweenDates(String consultantId,
                                                                   String clientId,
                                                                   LocalDateTime startDate,
                                                                   LocalDateTime endDate);

}
