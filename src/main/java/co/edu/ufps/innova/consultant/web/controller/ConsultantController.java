package co.edu.ufps.innova.consultant.web.controller;

import java.util.List;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.ufps.innova.consultant.domain.dto.Consultant;
import co.edu.ufps.innova.consultant.domain.service.IConsultantService;

@RestController
@RequiredArgsConstructor
@Api(tags = "consultant")
@RequestMapping("/consultant")
public class ConsultantController {

    private final IConsultantService service;

    @PostMapping
    @ApiOperation("Save a new Consultant")
    @ApiResponse(code = 201, message = "Created")
    public ResponseEntity<Consultant> save(@RequestBody Consultant consultant) {
        return new ResponseEntity<>(service.save(consultant), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update an existing Consultant")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Consultant not found")
    })
    public ResponseEntity<HttpStatus> update(@PathVariable String id, @RequestBody Consultant consultant) {
        return service.update(id, consultant)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @ApiOperation("List all Consultants")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Consultant>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get an Consultant by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Consultant not found")
    })
    public ResponseEntity<Consultant> findById(@PathVariable String id) {
        return service.findById(id)
                .map(consultant -> new ResponseEntity<>(consultant, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active")
    @ApiOperation("List all active Consultants")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Consultants not found")
    })
    public ResponseEntity<List<Consultant>> findByActive() {
        return service.findByActive()
                .map(consultants -> new ResponseEntity<>(consultants, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete an exiting Consultant")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Consultant not found")
    })
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        return service.delete(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
