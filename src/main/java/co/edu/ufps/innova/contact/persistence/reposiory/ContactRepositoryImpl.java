package co.edu.ufps.innova.contact.persistence.reposiory;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import co.edu.ufps.innova.contact.domain.dto.Contact;
import co.edu.ufps.innova.contact.domain.dto.ContactType;
import co.edu.ufps.innova.contact.persistence.entity.ContactEntity;
import co.edu.ufps.innova.contact.persistence.mapper.IContactMapper;
import co.edu.ufps.innova.contact.domain.repository.IContactRepository;

@Repository
@RequiredArgsConstructor
public class ContactRepositoryImpl implements IContactRepository {

    private final IContactMapper mapper;
    private final IContactCrudRepository repository;

    @Override
    public Contact save(Contact contact, String password) {
        ContactEntity entity = mapper.toContactEntity(contact);
        entity.setPassword(password);
        entity.setActive(true);
        entity.setUserType("CONTACT");
        return mapper.toContact(repository.save(entity));
    }

    @Override
    public Optional<Contact> findById(String id) {
        return repository.findById(id).map(mapper::toContact);
    }

    @Override
    public Optional<List<Contact>> findByType(ContactType type) {
        return repository.findByType(type).map(mapper::toContactList);
    }

    @Override
    public Optional<List<Contact>> findByRequestAccompaniment(boolean requestAccompaniment) {
        return repository.findByRequestAccompaniment(requestAccompaniment).map(mapper::toContactList);
    }

    @Override
    public Optional<List<Contact>> findAll() {
        return repository.findByUserType("CONTACT").map(mapper::toContactList);
        //return mapper.toContactList((List<ContactEntity>) repository.findAll());
    }

    @Override
    public void delete(Contact consultant) {
        repository.delete(mapper.toContactEntity(consultant));
    }
}
