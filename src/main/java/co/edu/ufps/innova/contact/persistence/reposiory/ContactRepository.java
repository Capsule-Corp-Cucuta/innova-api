package co.edu.ufps.innova.contact.persistence.reposiory;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import co.edu.ufps.innova.contact.domain.dto.Contact;
import co.edu.ufps.innova.contact.persistence.entity.ContactEntity;
import co.edu.ufps.innova.contact.persistence.mapper.IContactMapper;
import co.edu.ufps.innova.contact.domain.repository.IContactRepository;

@Repository
@RequiredArgsConstructor
public class ContactRepository implements IContactRepository {

    private final String USER_TYPE = "CONTACT";
    private final IContactMapper mapper;
    private final IContactCrudRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Contact save(Contact contact, String password) {
        ContactEntity entity = mapper.toContactEntity(contact);
        entity.setPassword(password);
        entity.setActive(true);
        entity.setUserType("CONTACT");
        return mapper.toContact(repository.save(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Contact>> findAll() {
        return repository.findByUserType(USER_TYPE).map(mapper::toContactList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Contact> findById(String id) {
        return repository.findById(id).map(mapper::toContact);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Contact>> findByRequestAccompaniment() {
        return repository.findByRequestAccompanimentAndUserType(true, USER_TYPE)
                .map(mapper::toContactList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Contact consultant) {
        repository.delete(mapper.toContactEntity(consultant));
    }
}
