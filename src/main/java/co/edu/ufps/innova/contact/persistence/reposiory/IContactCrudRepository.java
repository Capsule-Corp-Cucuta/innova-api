package co.edu.ufps.innova.contact.persistence.reposiory;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.contact.persistence.entity.ContactEntity;

public interface IContactCrudRepository extends CrudRepository<ContactEntity, String> {

    /**
     * Method for get all Contacts with user type
     *
     * @param userType of User (CONTACT by default)
     * @return all Contacts
     */
    Optional<List<ContactEntity>> findByUserType(String userType);

    /**
     * Method for get all Contacts with request accompaniment and user type
     *
     * @param requestAccompaniment of Contact (true by default)
     * @param userType             of User (CONTACT by default)
     * @return all Contacts who request accompaniment and are Contacts
     */
    Optional<List<ContactEntity>> findByRequestAccompanimentAndUserType(boolean requestAccompaniment, String userType);

}
