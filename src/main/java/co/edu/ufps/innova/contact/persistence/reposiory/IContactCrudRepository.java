package co.edu.ufps.innova.contact.persistence.reposiory;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.contact.domain.dto.ContactType;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.contact.persistence.entity.ContactEntity;

public interface IContactCrudRepository extends CrudRepository<ContactEntity, String> {

    Optional<List<ContactEntity>> findByType(ContactType type);

    Optional<List<ContactEntity>> findByUserType(String userType);

    Optional<List<ContactEntity>> findByRequestAccompaniment(boolean requestAccompaniment);

}
