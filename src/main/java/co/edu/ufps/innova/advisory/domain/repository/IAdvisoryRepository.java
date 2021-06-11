package co.edu.ufps.innova.advisory.domain.repository;

import java.util.Set;
import java.util.Optional;
import java.time.LocalDateTime;
import co.edu.ufps.innova.advisory.domain.dto.Advisory;

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
    Set<Advisory> findAll();

    /**
     * Method for find an Advisory by id
     *
     * @return Advisory with the given id
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
    Optional<Set<Advisory>> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Method for get all Advisories by consultant id
     *
     * @param consultantId consultant id
     * @return all Advisories with the given consultant id
     */
    Optional<Set<Advisory>> findByConsultant(String consultantId);

    /**
     * Method for get all Advisories by client id
     *
     * @param clientId client id
     * @return all Advisories with the given client id
     */
    Optional<Set<Advisory>> findByClient(String clientId);

    /**
     * Method for get all Advisories by consultant id and client id
     *
     * @param consultantId consultant id
     * @param clientId     client id
     * @return all Advisories with the given consultant id and client id
     */
    Optional<Set<Advisory>> findByConsultantAndClient(String consultantId, String clientId);

    /**
     * Method for get all Advisories by consultant id and between two dates
     *
     * @param consultantId consultant id
     * @param startDate    first date
     * @param endDate      second date
     * @return all Advisories with the given consultant id and two dates
     */
    Optional<Set<Advisory>> findByConsultantAndBetweenDates(String consultantId,
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
    Optional<Set<Advisory>> findByConsultantAndClientBetweenDates(String consultantId,
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
