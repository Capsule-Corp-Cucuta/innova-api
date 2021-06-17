package co.edu.ufps.innova.client.web.controller;

import java.util.List;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.ufps.innova.client.domain.dto.Client;
import co.edu.ufps.innova.client.domain.service.IClientService;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "client")
@RequestMapping("/client")
public class ClientController {

    private final IClientService service;

    @PostMapping
    @ApiOperation("Save a new Client")
    @ApiResponse(code = 201, message = "Created")
    public ResponseEntity<Client> save(@RequestBody String criteria) {
        JsonObject jsonObject = JsonParser.parseString(criteria).getAsJsonObject();
        return new ResponseEntity<>(service.save(
                jsonObject.get("contactId").getAsString(),
                jsonObject.get("consultantId").getAsString()),
                HttpStatus.CREATED
        );
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

    @GetMapping("/consultant/{consultantId}/active")
    @ApiOperation("List all active Clients by consultant id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Clients not found")
    })
    public ResponseEntity<List<Client>> findByConsultantIdAndActive(@PathVariable String consultantId) {
        return service.findByConsultantIdAndActive(consultantId)
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
