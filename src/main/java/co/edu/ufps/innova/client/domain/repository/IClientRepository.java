package co.edu.ufps.innova.client.domain.repository;

import java.util.Set;
import java.util.Optional;
import co.edu.ufps.innova.client.domain.dto.Client;

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
    Set<Client> findAll();

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
    Optional<Set<Client>> findByConsultant(String consultantId);

    /**
     * Method for get all Clients by Consultant and active
     *
     * @param consultantId id of the Consultant
     * @param active       of User
     * @return all Clients with the given consultant id
     */
    Optional<Set<Client>> findByConsultantIdAndActive(String consultantId, boolean active);

}
