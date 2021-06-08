package co.edu.ufps.innova.event.web.controller;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
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
import co.edu.ufps.innova.event.domain.dto.Event;
import co.edu.ufps.innova.event.domain.dto.EventType;
import co.edu.ufps.innova.event.domain.dto.EventState;
import co.edu.ufps.innova.event.domain.service.EventService;

@RestController
@Api(tags = "event")
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService service;

    @PostMapping
    @ApiOperation("Save a new Event")
    @ApiResponse(code = 201, message = "Created")
    public ResponseEntity<Event> save(@RequestBody Event event) {
        return new ResponseEntity<>(service.save(event), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update an existing Event")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Event not found")
    })
    public ResponseEntity<HttpStatus> update(@PathVariable("id") long id, @RequestBody Event event) {
        return service.update(id, event)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @ApiOperation("List all Events")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Event>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get an Event by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Event not found")
    })
    public ResponseEntity<Event> findById(@PathVariable("id") long id) {
        return service.findById(id)
                .map(event -> new ResponseEntity<>(event, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/type/{type}")
    @ApiOperation("Get an Event by type")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Events not found")
    })
    public ResponseEntity<List<Event>> findByType(@PathVariable("type") EventType type) {
        return service.findByType(type)
                .map(events -> new ResponseEntity<>(events, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/state/{state}")
    @ApiOperation("Get an Event by state")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Events not found")
    })
    public ResponseEntity<List<Event>> findByState(@PathVariable("state") EventState state) {
        return service.findByState(state)
                .map(events -> new ResponseEntity<>(events, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/between-dates")
    @ApiOperation("Get an Event between dates")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Events not found")
    })
    public ResponseEntity<List<Event>> findBetweenDates(String criteria) {
        JsonObject jsonObject = JsonParser.parseString(criteria).getAsJsonObject();
        LocalDateTime startDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("startDate").getAsString()), LocalTime.MIN);
        LocalDateTime endDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("endDate").getAsString()), LocalTime.MAX);
        return service.findBetweenDates(startDate, endDate)
                .map(events -> new ResponseEntity<>(events, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete an exiting Event")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Event not found")
    })
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        return service.delete(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
