package co.edu.ufps.innova.contact.domain.repository;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.contact.domain.dto.Contact;
import co.edu.ufps.innova.contact.domain.dto.ContactType;

public interface IContactRepository {

    /**
     * Method for persist a Contact
     *
     * @param contact  to save
     * @param password of User
     * @return saved Contact
     */
    Contact save(Contact contact, String password);

    /**
     * Method for get all Contacts
     *
     * @return all Contacts
     */
    public Optional<List<Contact>> findAll();

    /**
     * Method for find a Contact by id
     *
     * @param id of User
     * @return User with the given id
     */
    Optional<Contact> findById(String id);

    /**
     * Method for find a Contact by type
     *
     * @param type of Contact
     * @return Contact with the given type or Optional
     */
    Optional<List<Contact>> findByType(ContactType type);

    /**
     * Method for get all Contacts with request accompaniment and user type
     *
     * @return all Contacts who request accompaniment
     */
    Optional<List<Contact>> findByRequestAccompaniment();

    /**
     * Method for delete a Consultan
     *
     * @param consultant to delete
     */
    void delete(Contact consultant);

}
