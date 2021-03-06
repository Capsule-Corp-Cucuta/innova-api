package co.edu.ufps.innova.client.persistence.reposiroty;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import co.edu.ufps.innova.user.domain.dto.UserType;
import co.edu.ufps.innova.client.domain.dto.Client;
import co.edu.ufps.innova.client.persistence.entity.ClientEntity;
import co.edu.ufps.innova.client.persistence.mapper.IClientMapper;
import co.edu.ufps.innova.client.domain.repository.IClientRepository;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Repository
@RequiredArgsConstructor
public class ClientRepository implements IClientRepository {

    private final String USER_TYPE = UserType.CLIENTE.name();

    private final IClientMapper mapper;
    private final IClientCrudRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Client save(Client client, String password) {
        ClientEntity entity = mapper.toClientEntity(client);
        entity.setActive(true);
        entity.setPassword(password);
        entity.setUserType(USER_TYPE);
        return mapper.toClient(repository.save(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Client> findAll() {
        return mapper.toClientList((List<ClientEntity>) repository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Client> findById(String id) {
        return repository.findById(id).map(mapper::toClient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Client client) {
        repository.delete(mapper.toClientEntity(client));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Client>> findByConsultant(String consultantId) {
        return repository.findByConsultantId(consultantId).map(mapper::toClientList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Client>> findByConsultantIdAndActive(String consultantId, boolean active) {
        return repository.findByConsultantIdAndActive(consultantId, active).map(mapper::toClientList);
    }

}
