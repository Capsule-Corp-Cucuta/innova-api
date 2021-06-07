package co.edu.ufps.innova.user.domain.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.user.domain.dto.User;
import co.edu.ufps.innova.email.domain.dto.Email;
import org.springframework.mail.MailSendException;
import co.edu.ufps.innova.user.domain.repository.IUserRepository;
import co.edu.ufps.innova.email.domain.repository.IEmailRepository;
import co.edu.ufps.innova.authentication.domain.repository.IPasswordRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository repository;
    private final IPasswordRepository pwRepository;
    private final IEmailRepository IEmailRepository;

    public User save(User user) {
        String newPassword = pwRepository.generatePassword();
        User myUser = repository.save(user, pwRepository.encryptPassword(newPassword));

        try {
            Email email = new Email();
            email.setTo(myUser.getEmail());
            email.setSubject("Innova - Registro exitoso");
            email.setContent("Con esta contraseña podrá ingresar a la plataforma: \n " +
                    "\t " + newPassword +
                    "\n recomendamos cambie esta contraseña desde la plataforma apenas ingrese a la misma.");
            IEmailRepository.sendEmail(email);
        } catch (MailSendException e) {
            e.printStackTrace();
        }

        return myUser;
    }

    public boolean update(String id, User user) {
        return repository.update(id, user);
    }

    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public boolean delete(String id) {
        return findById(id).map(user -> {
            repository.delete(user);
            return true;
        }).orElse(false);
    }

    public String getPassword(String id) {
        return repository.getPassword(id);
    }

    public boolean changeState(String id) {
        return repository.changeState(id);
    }

    public boolean changePassword(String id, String oldPassword, String newPassword) {
        return pwRepository.validatePassword(oldPassword, getPassword(id)) &&
                repository.changePassword(id, pwRepository.encryptPassword(newPassword));
    }

    public boolean recoverPassword(String userEmail) {
        return findByEmail(userEmail).map(user -> {
            String newPassword = pwRepository.generatePassword();
            boolean updated = repository.changePassword(user.getId(), pwRepository.encryptPassword(newPassword));
            if (updated) {
                try {
                    Email email = new Email();
                    email.setTo(user.getEmail());
                    email.setSubject("Innova - Cambio de contraseña");
                    email.setContent("Con esta nueva contraseña podrá ingresar a la plataforma: \n " +
                            "\t " + newPassword +
                            "\n recomendamos cambie esta contraseña desde la plataforma apenas ingrese a la misma.");
                    IEmailRepository.sendEmail(email);
                } catch (MailSendException e) {
                    e.printStackTrace();
                }
            }
            return updated;
        }).orElse(false);
    }

}
