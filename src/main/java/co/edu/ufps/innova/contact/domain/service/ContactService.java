package co.edu.ufps.innova.contact.domain.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.email.domain.dto.Email;
import co.edu.ufps.innova.contact.domain.dto.Contact;
import co.edu.ufps.innova.contact.domain.dto.ContactType;
import co.edu.ufps.innova.user.domain.service.UserService;
import co.edu.ufps.innova.email.domain.repository.IEmailRepository;
import co.edu.ufps.innova.contact.domain.repository.IContactRepository;
import co.edu.ufps.innova.authentication.domain.repository.IPasswordRepository;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final UserService userService;
    private final IContactRepository repository;
    private final IEmailRepository IEmailRepository;
    private final IPasswordRepository passwordRepository;

    public Contact save(Contact contact) {
        if (userService.findById(contact.getId()).isPresent()) userService.delete(contact.getId());
        contact.setRegistrationDate(LocalDate.now());
        String userPassword = passwordRepository.generatePassword();
        Contact myContact = repository.save(contact, passwordRepository.encryptPassword(userPassword));

        Email email = new Email();
        email.setTo(myContact.getEmail());
        email.setSubject("Innova - Registro exitoso");
        email.setContent("Con esta contraseña podrá ingresar a la plataforma: \n " +
                "\t " + userPassword +
                "\n recomendamos cambie esta contraseña desde la plataforma apenas ingrese a la misma.");
        IEmailRepository.sendEmail(email);

        return myContact;
    }

    public boolean update(String id, Contact contact) {
        return findById(id).map(item -> {
            contact.setRegistrationDate(item.getRegistrationDate());
            repository.save(contact, userService.getPassword(id));
            return true;
        }).orElse(false);
    }

    public boolean requestAccompaniment(String id) {
        return findById(id).map(item -> {
            item.setRequestAccompaniment(true);
            repository.save(item, userService.getPassword(id));
            return true;
        }).orElse(false);
    }

    public Optional<Contact> findById(String id) {
        return repository.findById(id);
    }

    public Optional<List<Contact>> findByType(ContactType type) {
        return repository.findByType(type);
    }

    public Optional<List<Contact>> findByRequestAccompaniment() {
        return repository.findByRequestAccompaniment();
    }

    public Optional<List<Contact>> findAll() {
        return repository.findAll();
    }

    public boolean delete(String id) {
        return findById(id).map(contact -> {
            repository.delete(contact);
            return true;
        }).orElse(false);
    }

}
