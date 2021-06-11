package co.edu.ufps.innova.consultant.domain.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailSendException;
import co.edu.ufps.innova.user.domain.service.IUserService;
import co.edu.ufps.innova.consultant.domain.dto.Consultant;
import co.edu.ufps.innova.email.domain.service.IEmailService;
import co.edu.ufps.innova.consultant.domain.repository.IConsultantRepository;
import co.edu.ufps.innova.authentication.domain.service.IPasswordService;

@Service
@RequiredArgsConstructor
public class ConsultantService {

    private final IUserService userService;
    private final IEmailService emailService;
    private final IConsultantRepository repository;
    private final IPasswordService passwordService;

    public Consultant save(Consultant consultant) {
        if (userService.findById(consultant.getId()).isPresent()) userService.delete(consultant.getId());
        String userPassword = passwordService.generatePassword();
        Consultant newConsultant = repository.save(consultant, passwordService.encryptPassword(userPassword));
        try {
            emailService.sendNewUserEmail(newConsultant.getEmail(), userPassword);
        } catch (MailSendException e) {
            e.printStackTrace();
        }
        return newConsultant;
    }

    public boolean update(String id, Consultant consultant) {
        return findById(id)
                .map(item -> {
                    consultant.setId(id);
                    repository.save(consultant, userService.getPassword(id));
                    return true;
                })
                .orElse(false);
    }

    public List<Consultant> findAll() {
        return repository.findAll();
    }

    public Optional<Consultant> findById(String id) {
        return repository.findById(id);
    }

    public boolean delete(String id) {
        return findById(id).map(consultant -> {
            repository.delete(consultant);
            return true;
        }).orElse(false);
    }

    public Optional<List<Consultant>> findByActive() {
        return repository.findByActive();
    }

}
