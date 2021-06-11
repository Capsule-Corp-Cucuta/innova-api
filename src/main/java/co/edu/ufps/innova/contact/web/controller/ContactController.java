package co.edu.ufps.innova.contact.web.controller;

import java.util.List;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.ufps.innova.contact.domain.dto.Contact;
import co.edu.ufps.innova.contact.domain.service.ContactService;

@RestController
@Api(tags = "contact")
@RequiredArgsConstructor
@RequestMapping("/contact")
public class ContactController {

    private final ContactService service;

    @PostMapping
    @ApiOperation("Save a new Contact")
    @ApiResponse(code = 201, message = "Created")
    public ResponseEntity<Contact> save(@RequestBody Contact contact) {
        return new ResponseEntity<>(service.save(contact), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update an existing Contact")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Contact not found")
    })
    public ResponseEntity<HttpStatus> update(@PathVariable String id, @RequestBody Contact contact) {
        return service.update(id, contact)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/request-accompaniment")
    @ApiOperation("Request accompaniment to a existing Contact")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Contact not found")
    })
    public ResponseEntity<HttpStatus> requestAccompaniment(@PathVariable String id) {
        return service.requestAccompaniment(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @ApiOperation("List all Contacts")
    @ApiResponse(code = 200, message = "OK")
    private ResponseEntity<List<Contact>> findAll() {
        return service.findAll()
                .map(contacts -> new ResponseEntity<>(contacts, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a Contact by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Contact not found")
    })
    public ResponseEntity<Contact> findById(@PathVariable String id) {
        return service.findById(id)
                .map(contact -> new ResponseEntity<>(contact, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/request-accompaniment")
    @ApiOperation("List all Contacts who request accompaniment")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Contacts not found")
    })
    public ResponseEntity<List<Contact>> findByRequestAccompaniment() {
        return service.findByRequestAccompaniment()
                .map(contacts -> new ResponseEntity<>(contacts, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete an exiting Contact")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Contact not found")
    })
    private ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        return service.delete(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
