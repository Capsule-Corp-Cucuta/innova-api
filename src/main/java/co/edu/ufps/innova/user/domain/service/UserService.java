package co.edu.ufps.innova.user.domain.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.user.domain.dto.User;
import org.springframework.mail.MailSendException;
import co.edu.ufps.innova.email.domain.service.IEmailService;
import co.edu.ufps.innova.user.domain.repository.IUserRepository;
import co.edu.ufps.innova.authentication.domain.service.IPasswordService;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository repository;
    private final IEmailService emailService;
    private final IPasswordService passwordService;

    /**
     * {@inheritDoc}
     */
    @Override
    public User save(User user) {
        String newPassword = passwordService.generatePassword();
        User newUser = repository.save(user, passwordService.encryptPassword(newPassword));
        try {
            emailService.sendNewUserEmail(newUser.getEmail(), newPassword);
        } catch (MailSendException e) {
            e.printStackTrace();
        }
        return newUser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(String id, User user) {
        return repository.update(id, user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String id) {
        return findById(id).map(user -> {
            repository.delete(user);
            return true;
        }).orElse(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPassword(String id) {
        return repository.getPassword(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean changeState(String id) {
        return repository.changeState(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean changePassword(String id, String oldPassword, String newPassword) {
        return passwordService.validatePassword(oldPassword, getPassword(id)) &&
                repository.changePassword(id, passwordService.encryptPassword(newPassword));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean recoverPassword(String userEmail) {
        return findByEmail(userEmail).map(user -> {
            String newPassword = passwordService.generatePassword();
            boolean updated = repository.changePassword(user.getId(), passwordService.encryptPassword(newPassword));
            if (updated) {
                try {
                    emailService.sendRecoverPasswordEmail(userEmail, newPassword);
                } catch (MailSendException e) {
                    e.printStackTrace();
                }
            }
            return updated;
        }).orElse(false);
    }

}
