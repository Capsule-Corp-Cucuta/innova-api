package co.edu.ufps.innova.consultant.domain.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.consultant.domain.dto.Consultant;
import co.edu.ufps.innova.user.domain.service.UserService;
import co.edu.ufps.innova.authentication.domain.service.PasswordService;
import co.edu.ufps.innova.consultant.domain.repository.IConsultantRepository;

@Service
@RequiredArgsConstructor
public class ConsultantService {

    private final UserService userService;
    private final PasswordService pwService;
    private final IConsultantRepository repository;

    public Consultant save(Consultant consultant) {
        String userPassword;
        if (userService.findById(consultant.getId()).isPresent()) {
            userPassword = userService.getPassword(consultant.getId());
            userService.delete(consultant.getId());
        } else {
            userPassword = pwService.generatePassword();
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

    public boolean delete(String id) {
        return findById(id).map(consultant -> {
            repository.delete(consultant);
            return true;
        }).orElse(false);
    }

}
