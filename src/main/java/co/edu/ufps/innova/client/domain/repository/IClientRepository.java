package co.edu.ufps.innova.client.domain.repository;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.client.domain.dto.Client;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
public interface IClientRepository {

    /**
     * Method for persist a Client
     *
     * @param client   to save
     * @param password of User
     * @return the saved Client
     */
    Client save(Client client, String password);

    /**
     * Method for get all Clients
     *
     * @return all Clients
     */
    List<Client> findAll();

    /**
     * Method for find a Client by id
     *
     * @param id of User
     * @return Client with the given id
     */
    Optional<Client> findById(String id);

    /**
     * Method for delete a Client
     *
     * @param client to delete
     */
    void delete(Client client);

    /**
     * Method for get all Clients by Consultant
     *
     * @param consultantId consultant id
     * @return all Clients with the given consultant id
     */
    Optional<List<Client>> findByConsultant(String consultantId);

    /**
     * Method for get all Clients by Consultant and active
     *
     * @param consultantId id of the Consultant
     * @param active       of User
     * @return all Clients with the given consultant id
     */
    Optional<List<Client>> findByConsultantIdAndActive(String consultantId, boolean active);

}
