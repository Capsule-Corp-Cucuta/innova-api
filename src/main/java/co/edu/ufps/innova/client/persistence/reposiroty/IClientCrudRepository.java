package co.edu.ufps.innova.client.persistence.reposiroty;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.contact.domain.dto.ContactType;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.client.persistence.entity.ClientEntity;
import co.edu.ufps.innova.consultant.persistence.entity.ConsultantEntity;

public interface IClientCrudRepository extends CrudRepository<ClientEntity, String> {

    Optional<List<ClientEntity>> findByType(ContactType type);

    Optional<List<ClientEntity>> findByConsultant(ConsultantEntity consultantEntity);

}
