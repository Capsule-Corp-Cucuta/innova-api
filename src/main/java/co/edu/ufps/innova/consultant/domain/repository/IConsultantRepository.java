package co.edu.ufps.innova.consultant.domain.repository;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.consultant.domain.dto.Consultant;

public interface IConsultantRepository {

    /**
     * Method for persist a Consultant
     *
     * @param consultant to save
     * @param password   of User
     * @return the saved Consultant
     */
    Consultant save(Consultant consultant, String password);

    /**
     * Method for list all Consultants
     *
     * @return all Consultants
     */
    List<Consultant> findAll();

    /**
     * Method for find a Consultant by Id
     *
     * @param id of the User
     * @return Consultant with the given id or Optional
     */
    Optional<Consultant> findById(String id);

    /**
     * Method for delete a Consultant
     *
     * @param consultant to delete
     */
    void delete(Consultant consultant);

    /**
     * Method for get a list of active Consultants
     *
     * @return all active Consultants
     */
    Optional<List<Consultant>> findByActive();

}
