package co.edu.ufps.innova.client.domain.service;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.client.domain.dto.Client;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
public interface IClientService {

    /**
     * Method for persist a Client
     *
     * @param contactId    of Contact
     * @param consultantId of Consultant
     * @return the saved Client
     */
    Client save(String contactId, String consultantId);

    /**
     * Method for update a Client
     *
     * @param id     of the Client
     * @param client to update
     * @return true if update the Client
     */
    boolean update(String id, Client client);

    /**
     * Method for get all Clients
     *
     * @return all Clients
     */
    List<Client> findAll();

    /**
     * Method for find a Client by id
     *
     * @param id of the Client
     * @return the Client with the given id
     */
    Optional<Client> findById(String id);

    /**
     * Method for delete a Client
     *
     * @param id of the Client
     * @return true if delete the Client
     */
    boolean delete(String id);

    /**
     * Method for get all Clients by Consultant id
     *
     * @param consultantId of Consultant
     * @return all Clients with the given consultant id
     */
    Optional<List<Client>> findByConsultant(String consultantId);

    /**
     * Method for get all Clients by Consultant id and active
     *
     * @param consultantId of Consultant
     * @return all active Clients with the given consultant id
     */
    Optional<List<Client>> findByConsultantIdAndActive(String consultantId);

}
