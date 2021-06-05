package co.edu.ufps.innova.client.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.ufps.innova.client.domain.dto.Client;
import co.edu.ufps.innova.contact.domain.dto.ContactType;
import co.edu.ufps.innova.client.domain.service.ClientService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService service;

    @PostMapping("/{contactId}/consultant/{consultantId}")
    public ResponseEntity<Client> save(@PathVariable String contactId, @PathVariable String consultantId) {
        return new ResponseEntity<>(service.save(contactId, consultantId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable String id, @RequestBody Client client) {
        return service.update(id, client) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable String id) {
        return service.findById(id)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Client>> findByType(@PathVariable ContactType type) {
        return service.findByType(type)
                .map(clients -> new ResponseEntity<>(clients, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/consultant/{consultantId}")
    public ResponseEntity<List<Client>> findByConsultant(@PathVariable String consultantId) {
        return service.findByConsultant(consultantId)
                .map(clients -> new ResponseEntity<>(clients, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        return service.delete(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
