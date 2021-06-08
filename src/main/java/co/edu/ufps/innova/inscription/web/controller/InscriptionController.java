package co.edu.ufps.innova.inscription.web.controller;

import java.util.List;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Inscription> save(@RequestBody Inscription inscription) {
        Inscription newInscription = service.save(inscription);
        return newInscription != null
                ? new ResponseEntity<>(newInscription, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/event/{eventId}/user/{userId}")
    public ResponseEntity<HttpStatus> update(@PathVariable("eventId") long eventId,
                                             @PathVariable("userId") String userId,
                                             @RequestBody Inscription inscription) {
        return service.update(eventId, userId, inscription)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody List<Inscription> inscriptions) {
        service.update(inscriptions);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/event/{eventId}/user/{userId}")
    public ResponseEntity<Inscription> findById(@PathVariable("eventId") long eventId,
                                                @PathVariable("userId") String userId) {
        return service.findById(eventId, userId)
                .map(inscription -> new ResponseEntity<>(inscription, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Inscription>> findByEventId(@PathVariable("eventId") long eventId) {
        return service.findByEventId(eventId)
                .map(inscriptions -> new ResponseEntity<>(inscriptions, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/event/{eventId}/user/{userId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("eventId") long eventId,
                                             @PathVariable("userId") String userId) {
        return service.delete(eventId, userId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
