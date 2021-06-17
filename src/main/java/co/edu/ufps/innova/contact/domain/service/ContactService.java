package co.edu.ufps.innova.contact.domain.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailSendException;
import co.edu.ufps.innova.contact.domain.dto.Contact;
import co.edu.ufps.innova.user.domain.service.IUserService;
import co.edu.ufps.innova.email.domain.service.IEmailService;
import co.edu.ufps.innova.contact.domain.repository.IContactRepository;
import co.edu.ufps.innova.authentication.domain.service.IPasswordService;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Service
@RequiredArgsConstructor
public class ContactService implements IContactService {

    private final IUserService userService;
    private final IEmailService emailService;
    private final IContactRepository repository;
    private final IPasswordService passwordService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Contact save(Contact contact) {
        if (userService.findById(contact.getId()).isPresent()) userService.delete(contact.getId());
        contact.setRegistrationDate(LocalDate.now());
        String userPassword = passwordService.generatePassword();
        Contact newContact = repository.save(contact, passwordService.encryptPassword(userPassword));
        try {
            emailService.sendNewUserEmail(newContact.getEmail(), userPassword);
        } catch (MailSendException e) {
            e.printStackTrace();
        }
        return newContact;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(String id, Contact contact) {
        return findById(id).map(item -> {
            contact.setId(id);
            contact.setRegistrationDate(item.getRegistrationDate());
            repository.save(contact, userService.getPassword(id));
            return true;
        }).orElse(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Contact>> findAll() {
        return repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Contact> findById(String id) {
        return repository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String id) {
        return findById(id).map(contact -> {
            repository.delete(contact);
            return true;
        }).orElse(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Contact>> findByRequestAccompaniment() {
        return repository.findByRequestAccompaniment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean requestAccompaniment(String id) {
        return findById(id).map(item -> {
            item.setRequestAccompaniment(true);
            repository.save(item, userService.getPassword(id));
            return true;
        }).orElse(false);
    }

}
