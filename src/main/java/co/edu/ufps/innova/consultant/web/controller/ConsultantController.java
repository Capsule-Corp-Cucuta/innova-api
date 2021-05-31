package co.edu.ufps.innova.consultant.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.ufps.innova.consultant.domain.dto.Consultant;
import co.edu.ufps.innova.consultant.domain.service.ConsultantService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consultant")
public class ConsultantController {

    private final ConsultantService service;

    @PostMapping
    public ResponseEntity<Consultant> save(@RequestBody Consultant consultant) {
        return new ResponseEntity<>(service.save(consultant), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable String id, @RequestBody Consultant consultant) {
        return service.update(id, consultant) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Consultant>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultant> findById(@PathVariable String id) {
        return service.findById(id)
                .map(consultant -> new ResponseEntity<>(consultant, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Consultant> findByCode(@PathVariable String code) {
        return service.findByCode(code)
                .map(consultant -> new ResponseEntity<>(consultant, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        return service.delete(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
