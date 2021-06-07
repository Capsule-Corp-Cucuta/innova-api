package co.edu.ufps.innova.client.web.controller;

import java.util.List;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.ufps.innova.client.domain.dto.Client;
import co.edu.ufps.innova.contact.domain.dto.ContactType;
import co.edu.ufps.innova.client.domain.service.ClientService;

@RestController
@RequiredArgsConstructor
@Api(tags = "consultant")
@RequestMapping("/client")
public class ClientController {

    private final ClientService service;

    @PostMapping("/{contactId}/consultant/{consultantId}")
    @ApiOperation("Save a new Client")
    @ApiResponse(code = 201, message = "Created")
    public ResponseEntity<Client> save(@PathVariable String contactId, @PathVariable String consultantId) {
        return new ResponseEntity<>(service.save(contactId, consultantId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update an existing Client")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Client not found")
    })
    public ResponseEntity<HttpStatus> update(@PathVariable String id, @RequestBody Client client) {
        return service.update(id, client) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @ApiOperation("List all Clients")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Client>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get an Client by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Client not found")
    })
    public ResponseEntity<Client> findById(@PathVariable String id) {
        return service.findById(id)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/type/{type}")
    @ApiOperation("List all Clients by contact type")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Clients not found")
    })
    public ResponseEntity<List<Client>> findByType(@PathVariable ContactType type) {
        return service.findByType(type)
                .map(clients -> new ResponseEntity<>(clients, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/consultant/{consultantId}")
    @ApiOperation("List all Clients by consultant id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Clients not found")
    })
    public ResponseEntity<List<Client>> findByConsultant(@PathVariable String consultantId) {
        return service.findByConsultant(consultantId)
                .map(clients -> new ResponseEntity<>(clients, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ApiOperation("Delete an exiting Client")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Client not found")
    })
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        return service.delete(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
