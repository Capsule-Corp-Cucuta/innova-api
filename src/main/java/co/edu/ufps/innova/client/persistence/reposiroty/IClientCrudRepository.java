package co.edu.ufps.innova.client.persistence.reposiroty;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.contact.domain.dto.ContactType;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.client.persistence.entity.ClientEntity;

public interface IClientCrudRepository extends CrudRepository<ClientEntity, String> {

    /**
     * Method for get all Clients by contact type
     *
     * @param type Contact type
     * @return all Clients with the given contact type
     */
    Optional<List<ClientEntity>> findByType(ContactType type);

    /**
     * Method for get all Clients by Consultant
     *
     * @param consultantId id of the Consultant
     * @return all Clients with the given consultant id
     */
    Optional<List<ClientEntity>> findByConsultantId(String consultantId);

}
