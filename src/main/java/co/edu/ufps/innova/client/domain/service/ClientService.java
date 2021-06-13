package co.edu.ufps.innova.client.domain.service;

import java.util.List;
import java.util.Optional;

import co.edu.ufps.innova.inscription.domain.dto.Inscription;
import co.edu.ufps.innova.inscription.domain.service.InscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import co.edu.ufps.innova.client.domain.dto.Client;
import co.edu.ufps.innova.user.domain.service.IUserService;
import co.edu.ufps.innova.contact.domain.service.ContactService;
import co.edu.ufps.innova.client.domain.mapper.IClientDomainMapper;
import co.edu.ufps.innova.client.domain.repository.IClientRepository;
import co.edu.ufps.innova.consultant.domain.service.ConsultantService;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final IUserService userService;
    private final IClientDomainMapper mapper;
    private final IClientRepository repository;
    private final ContactService contactService;
    private final ConsultantService consultantService;
    private final InscriptionService inscriptionService;

    public Client save(String contactId, String consultantId) {
        inscriptionService.findByUserId(contactId).map(inscriptions -> {
            inscriptionService.deleteAll(inscriptions);
            return inscriptions;
        });

        return contactService.findById(contactId).map(contact -> {
            if (consultantService.findById(consultantId).isPresent()) {
                String userPassword = userService.getPassword(contactId);
                contactService.delete(contactId);
                Client client = mapper.toClient(contact);
                client.setConsultantId(consultantId);
                return repository.save(client, userPassword);
            }
            return null;
        }).orElse(null);
    }

    public boolean update(String id, Client client) {
        return findById(id).map(item -> {
            client.setId(id);
            repository.save(client, userService.getPassword(id));
            return true;
        }).orElse(false);
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Optional<Client> findById(String id) {
        return repository.findById(id);
    }

    public boolean delete(String id) {
        return findById(id).map(client -> {
            repository.delete(client);
            return true;
        }).orElse(false);
    }

    public Optional<List<Client>> findByConsultant(String consultantId) {
        return repository.findByConsultant(consultantId);
    }

    public Optional<List<Client>> findByConsultantIdAndActive(String consultantId) {
        return repository.findByConsultantIdAndActive(consultantId, true);
    }

}
