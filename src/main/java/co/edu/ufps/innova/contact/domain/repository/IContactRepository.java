package co.edu.ufps.innova.contact.domain.repository;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.contact.domain.dto.Contact;
import co.edu.ufps.innova.contact.domain.dto.ContactType;

public interface IContactRepository {

    Contact save(Contact contact, String password);

    Optional<Contact> findById(String id);

    Optional<List<Contact>> findByType(ContactType type);

    Optional<List<Contact>> findByRequestAccompaniment(boolean requestAccompaniment);

    public Optional<List<Contact>> findAll();

    void delete(Contact consultant);

}
