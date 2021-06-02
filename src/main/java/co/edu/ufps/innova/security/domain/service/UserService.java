package co.edu.ufps.innova.security.domain.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.security.domain.dto.User;
import co.edu.ufps.innova.security.domain.repository.IUserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordService pwService;
    private final IUserRepository repository;

    public User save(User user) {
        return repository.save(user, pwService.generatePassword());
    }

    public boolean update(String id, User user) {
        return findById(id).map(item -> {
            // TODO update fields
            repository.save(item, getPassword(id));
            return true;
        }).orElse(false);
    }

    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public boolean delete(String id) {
        return findById(id).map(user -> {
            repository.delete(user);
            return true;
        }).orElse(false);
    }

    public String getUserType(String id) {
        return repository.getUserType(id);
    }

    public String getPassword(String id) {
        return repository.getPassword(id);
    }

    public boolean changeState(String id) {
        return repository.changeState(id);
    }

}
