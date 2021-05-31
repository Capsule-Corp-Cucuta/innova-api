package co.edu.ufps.innova.client.persistence.reposiroty;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import co.edu.ufps.innova.client.domain.dto.Client;
import co.edu.ufps.innova.contact.domain.dto.ContactType;
import co.edu.ufps.innova.consultant.domain.dto.Consultant;
import co.edu.ufps.innova.client.persistence.entity.ClientEntity;
import co.edu.ufps.innova.client.persistence.mapper.IClientMapper;
import co.edu.ufps.innova.client.domain.repository.IClientRepository;
import co.edu.ufps.innova.consultant.persistence.mapper.IConsultantMapper;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements IClientRepository {

    private final IClientMapper mapper;
    private final IClientCrudRepository repository;
    private final IConsultantMapper consultantMapper;

    @Override
    public Client save(Client client, String password) {
        ClientEntity entity = mapper.toClientEntity(client);
        entity.setPassword(password);
        entity.setActive(true);
        entity.setUserType("CLIENT");
        return mapper.toClient(repository.save(entity));
    }

    @Override
    public Optional<Client> findById(String id) {
        return repository.findById(id).map(mapper::toClient);
    }

    @Override
    public Optional<List<Client>> findByType(ContactType type) {
        return repository.findByType(type).map(mapper::toClientList);
    }

    @Override
    public Optional<List<Client>> findByConsultant(Consultant consultant) {
        return repository.findByConsultant(consultantMapper.toConsultantEntity(consultant))
                .map(mapper::toClientList);
    }

    @Override
    public List<Client> findAll() {
        return mapper.toClientList((List<ClientEntity>) repository.findAll());
    }

    @Override
    public void delete(Client client) {
        repository.delete(mapper.toClientEntity(client));
    }
}
