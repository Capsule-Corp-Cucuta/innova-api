package co.edu.ufps.innova.client.domain.repository;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.client.domain.dto.Client;
import co.edu.ufps.innova.contact.domain.dto.ContactType;
import co.edu.ufps.innova.consultant.domain.dto.Consultant;

public interface IClientRepository {

    Client save(Client client, String password);

    Optional<Client> findById(String id);

    Optional<List<Client>> findByType(ContactType type);

    Optional<List<Client>> findByConsultant(Consultant consultant);

    List<Client> findAll();

    void delete(Client client);

}
