package co.edu.ufps.innova.client.persistence.reposiroty;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.client.persistence.entity.ClientEntity;

public interface IClientCrudRepository extends CrudRepository<ClientEntity, String> {

    /**
     * Method for get all Clients by Consultant
     *
     * @param consultantId id of the Consultant
     * @return all Clients with the given consultant id
     */
    Optional<List<ClientEntity>> findByConsultantId(String consultantId);

    /**
     * Method for get all Clients by Consultant and active
     *
     * @param consultantId id of the Consultant
     * @param active       of User
     * @return all Clients with the given consultant id
     */
    Optional<List<ClientEntity>> findByConsultantIdAndActive(String consultantId, boolean active);

}
