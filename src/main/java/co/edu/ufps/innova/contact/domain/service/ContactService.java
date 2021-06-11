package co.edu.ufps.innova.contact.domain.service;

import java.util.Set;
import java.util.Optional;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailSendException;
import co.edu.ufps.innova.contact.domain.dto.Contact;
import co.edu.ufps.innova.user.domain.service.UserService;
import co.edu.ufps.innova.email.domain.service.IEmailService;
import co.edu.ufps.innova.contact.domain.repository.IContactRepository;
import co.edu.ufps.innova.authentication.domain.repository.IPasswordRepository;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final UserService userService;
    private final IEmailService emailService;
    private final IContactRepository repository;
    private final IPasswordRepository passwordRepository;

    public Contact save(Contact contact) {
        if (userService.findById(contact.getId()).isPresent()) userService.delete(contact.getId());
        contact.setRegistrationDate(LocalDate.now());
        String userPassword = passwordRepository.generatePassword();
        Contact newContact = repository.save(contact, passwordRepository.encryptPassword(userPassword));
        try {
            emailService.sendNewUserEmail(newContact.getEmail(), userPassword);
        } catch (MailSendException e) {
            e.printStackTrace();
        }
        return newContact;
    }

    public boolean update(String id, Contact contact) {
        return findById(id).map(item -> {
            contact.setId(id);
            contact.setRegistrationDate(item.getRegistrationDate());
            repository.save(contact, userService.getPassword(id));
            return true;
        }).orElse(false);
    }

    public Optional<Set<Contact>> findAll() {
        return repository.findAll();
    }

    public Optional<Contact> findById(String id) {
        return repository.findById(id);
    }

    public boolean delete(String id) {
        return findById(id).map(contact -> {
            repository.delete(contact);
            return true;
        }).orElse(false);
    }

    public Optional<Set<Contact>> findByRequestAccompaniment() {
        return repository.findByRequestAccompaniment();
    }

    public boolean requestAccompaniment(String id) {
        return findById(id).map(item -> {
            item.setRequestAccompaniment(true);
            repository.save(item, userService.getPassword(id));
            return true;
        }).orElse(false);
    }

}
