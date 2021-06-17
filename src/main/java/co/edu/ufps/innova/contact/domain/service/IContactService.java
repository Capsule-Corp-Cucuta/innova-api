package co.edu.ufps.innova.contact.domain.service;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.contact.domain.dto.Contact;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
public interface IContactService {

    /**
     * Method for persist a Contact
     *
     * @param contact to persist
     * @return the saved Contact
     */
    Contact save(Contact contact);

    /**
     * Method for update a Contact
     *
     * @param id      of the Contact
     * @param contact to update
     * @return true if the Contact was updated
     */
    boolean update(String id, Contact contact);

    /**
     * Method for list all Contacts
     *
     * @return all Contacts
     */
    Optional<List<Contact>> findAll();

    /**
     * Method for find a Contact by id
     *
     * @param id of the Contact
     * @return the Contact with the given id
     */
    Optional<Contact> findById(String id);

    /**
     * Method for delete a Contact
     *
     * @param id of the Contact
     * @return true if delete Contact
     */
    boolean delete(String id);

    /**
     * Method for find contacts who request accompaniment
     *
     * @return all contacts who request accompaniment
     */
    Optional<List<Contact>> findByRequestAccompaniment();

    /**
     * Method for a Contact request accompaniment
     *
     * @param id of the Contact
     * @return true if request accompaniment correctly
     */
    boolean requestAccompaniment(String id);

}
