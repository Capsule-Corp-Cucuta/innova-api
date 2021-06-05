package co.edu.ufps.innova.contact.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.ufps.innova.contact.domain.dto.Contact;
import co.edu.ufps.innova.contact.domain.dto.ContactType;
import co.edu.ufps.innova.contact.domain.service.ContactService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contact")
public class ContactController {

    private final ContactService service;

    @PostMapping
    public ResponseEntity<Contact> save(@RequestBody Contact contact) {
        return new ResponseEntity<>(service.save(contact), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable String id, @RequestBody Contact contact) {
        return service.update(id, contact)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/request-accompaniment")
    public ResponseEntity<HttpStatus> requestAccompaniment(@PathVariable String id) {
        return service.requestAccompaniment(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    private ResponseEntity<List<Contact>> findAll() {
        return service.findAll()
                .map(contacts -> new ResponseEntity<>(contacts, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> findById(@PathVariable String id) {
        return service.findById(id)
                .map(contact -> new ResponseEntity<>(contact, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Contact>> findByType(@PathVariable ContactType type) {
        return service.findByType(type)
                .map(contacts -> new ResponseEntity<>(contacts, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/request-accompaniment")
    public ResponseEntity<List<Contact>> findByRequestAccompaniment() {
        return service.findByRequestAccompaniment()
                .map(contacts -> new ResponseEntity<>(contacts, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        return service.delete(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
