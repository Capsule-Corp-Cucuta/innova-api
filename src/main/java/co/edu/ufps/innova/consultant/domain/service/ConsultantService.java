package co.edu.ufps.innova.consultant.domain.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.user.domain.service.UserService;
import co.edu.ufps.innova.consultant.domain.dto.Consultant;
import co.edu.ufps.innova.consultant.domain.repository.IConsultantRepository;
import co.edu.ufps.innova.authentication.domain.repository.IPasswordRepository;

@Service
@RequiredArgsConstructor
public class ConsultantService {

    private final UserService userService;
    private final IConsultantRepository repository;
    private final IPasswordRepository passwordRepository;

    public Consultant save(Consultant consultant) {
        String userPassword;
        if (userService.findById(consultant.getId()).isPresent()) {
            userPassword = userService.getPassword(consultant.getId());
            userService.delete(consultant.getId());
        } else {
            userPassword = passwordRepository.encryptPassword(passwordRepository.generatePassword());
        }
        return repository.save(consultant, userPassword);
    }

    public boolean update(String id, Consultant consultant) {
        return findById(id)
                .map(item -> {
                    repository.save(consultant, userService.getPassword(id));
                    return true;
                })
                .orElse(false);
    }

    public Optional<Consultant> findById(String id) {
        return repository.findById(id);
    }

    public Optional<Consultant> findByCode(String code) {
        return repository.findByCode(code);
    }

    public List<Consultant> findAll() {
        return repository.findAll();
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
