package co.edu.ufps.innova.inscription.web.controller;

import java.util.List;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.ufps.innova.inscription.domain.dto.Inscription;
import co.edu.ufps.innova.inscription.domain.service.InscriptionService;

@RestController
@Api("inscription")
@RequiredArgsConstructor
@RequestMapping("/inscription")
public class InscriptionController {

    private final InscriptionService service;

    @PostMapping
    @ApiOperation("Save a new Inscription")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<Inscription> save(@RequestBody Inscription inscription) {
        Inscription newInscription = service.save(inscription);
        return newInscription != null
                ? new ResponseEntity<>(newInscription, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    @ApiOperation("Take attendance")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<HttpStatus> takeAttendance(@RequestBody List<Inscription> inscriptions) {
        service.takeAttendance(inscriptions);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/event/{eventId}/user/{userId}")
    @ApiOperation("Get an Inscription by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Inscription not found")
    })
    public ResponseEntity<Inscription> findById(@PathVariable("eventId") long eventId,
                                                @PathVariable("userId") String userId) {
        return service.findById(eventId, userId)
                .map(inscription -> new ResponseEntity<>(inscription, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/event/{eventId}")
    @ApiOperation("Get an Inscription by event id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Inscription not found")
    })
    public ResponseEntity<List<Inscription>> findByEventId(@PathVariable("eventId") long eventId) {
        return service.findByEventId(eventId)
                .map(inscriptions -> new ResponseEntity<>(inscriptions, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/event/{eventId}/user/{userId}")
    @ApiOperation("Delete an Inscription")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Inscription not found")
    })
    public ResponseEntity<HttpStatus> delete(@PathVariable("eventId") long eventId,
                                             @PathVariable("userId") String userId) {
        return service.delete(eventId, userId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
