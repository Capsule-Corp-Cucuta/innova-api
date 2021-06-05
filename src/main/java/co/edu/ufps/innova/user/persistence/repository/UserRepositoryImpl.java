package co.edu.ufps.innova.user.persistence.repository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import co.edu.ufps.innova.user.domain.dto.User;
import co.edu.ufps.innova.user.persistence.entity.UserEntity;
import co.edu.ufps.innova.user.persistence.mapper.IUserMapper;
import co.edu.ufps.innova.user.domain.repository.IUserRepository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements IUserRepository {

    private final IUserMapper mapper;
    private final IUserCrudRepository repository;

    @Override
    public User save(User user, String password) {
        UserEntity entity = mapper.toUserEntity(user);
        entity.setPassword(password);
        entity.setActive(true);
        return mapper.toUser(repository.save(entity));
    }

    @Override
    public Optional<User> findById(String id) {
        return repository.findById(id).map(mapper::toUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toUser);
    }

    @Override
    public List<User> findAll() {
        return mapper.toUsers((List<UserEntity>) repository.findAll());
    }

    @Override
    public void delete(User user) {
        repository.delete(mapper.toUserEntity(user));
    }

    @Override
    public boolean changeState(String id) {
        return repository.findById(id).map(userEntity -> {
            userEntity.setActive(!userEntity.getActive());
            repository.save(userEntity);
            return true;
        }).orElse(false);
    }

    @Override
    public String getUserType(String id) {
        return repository.findById(id).map(UserEntity::getUserType).orElse(null);
    }

    @Override
    public String getPassword(String id) {
        return repository.findById(id).map(UserEntity::getPassword).orElse(null);
    }

}
