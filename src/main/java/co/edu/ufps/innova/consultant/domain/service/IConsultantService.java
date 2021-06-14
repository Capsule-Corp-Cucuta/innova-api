package co.edu.ufps.innova.consultant.domain.service;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.consultant.domain.dto.Consultant;

public interface IConsultantService {

    /**
     * Method for persist a Consultant
     *
     * @param consultant to save
     * @return the saved Consultant
     */
    Consultant save(Consultant consultant);

    /**
     * Method for update a Consultant
     *
     * @param id of the Consultant
     * @param consultant to update
     * @return true if the Consultant was updated
     */
    boolean update(String id, Consultant consultant);

    /**
     * Method for list all Consultants
     *
     * @return all consultants
     */
    List<Consultant> findAll();

    /**
     * Method for find a Consultant by id
     *
     * @param id of the Consultant
     * @return the Consultant with the given id
     */
    Optional<Consultant> findById(String id);

    /**
     * Method for delete a Consultant
     *
     * @param id of the Consultant
     * @return true if delete the Consultant
     */
    boolean delete(String id);

    /**
     * Method for list all active Consultants
     *
     * @return List of all active Consultants
     */
    Optional<List<Consultant>> findByActive();

}
