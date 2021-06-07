package co.edu.ufps.innova.consultant.domain.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.email.domain.dto.Email;
import co.edu.ufps.innova.user.domain.service.UserService;
import co.edu.ufps.innova.consultant.domain.dto.Consultant;
import co.edu.ufps.innova.email.domain.repository.IEmailRepository;
import co.edu.ufps.innova.consultant.domain.repository.IConsultantRepository;
import co.edu.ufps.innova.authentication.domain.repository.IPasswordRepository;

@Service
@RequiredArgsConstructor
public class ConsultantService {

    private final UserService userService;
    private final IConsultantRepository repository;
    private final IEmailRepository IEmailRepository;
    private final IPasswordRepository passwordRepository;

    public Consultant save(Consultant consultant) {
        if (userService.findById(consultant.getId()).isPresent()) userService.delete(consultant.getId());
        String userPassword = passwordRepository.generatePassword();
        Consultant myConsultant = repository.save(consultant, passwordRepository.encryptPassword(userPassword));

        Email email = new Email();
        email.setTo(myConsultant.getEmail());
        email.setSubject("Innova - Registro exitoso");
        email.setContent("Con esta contraseña podrá ingresar a la plataforma: \n " +
                "\t " + userPassword +
                "\n recomendamos cambie esta contraseña desde la plataforma apenas ingrese a la misma.");
        IEmailRepository.sendEmail(email);

        return myConsultant;
    }

    public boolean update(String id, Consultant consultant) {
        return findById(id)
                .map(item -> {
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

    public Optional<Consultant> findByCode(String code) {
        return repository.findByCode(code);
    }

    public Optional<List<Consultant>> findByActive() {
        return repository.findByActive();
    }

    public boolean delete(String id) {
        return findById(id).map(consultant -> {
            repository.delete(consultant);
            return true;
        }).orElse(false);
    }

}
