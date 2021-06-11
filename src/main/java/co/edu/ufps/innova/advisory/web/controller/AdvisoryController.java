package co.edu.ufps.innova.advisory.web.controller;

import java.util.Set;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.ufps.innova.advisory.domain.dto.Advisory;
import co.edu.ufps.innova.advisory.domain.service.AdvisoryService;

@RestController
@Api(tags = "advisory")
@RequiredArgsConstructor
@RequestMapping("/advisory")
public class AdvisoryController {

    private final AdvisoryService service;

    @PostMapping
    @ApiOperation("Save a new Advisory")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<Advisory> save(@RequestBody Advisory advisory) {
        Advisory newAdvisory = service.save(advisory);
        return newAdvisory != null
                ? new ResponseEntity<>(newAdvisory, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update an existing Advisory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<HttpStatus> update(@PathVariable("id") long id, @RequestBody Advisory advisory) {
        return service.update(id, advisory)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    @ApiOperation("List all Advisories")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Set<Advisory>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get an Advisory by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisory not found")
    })
    public ResponseEntity<Advisory> findById(@PathVariable("id") long id) {
        return service.findById(id)
                .map(advisory -> new ResponseEntity<>(advisory, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/between-dates")
    @ApiOperation("List all Advisories between two dates")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<Set<Advisory>> findBetweenDates(String criteria) {
        JsonObject jsonObject = JsonParser.parseString(criteria).getAsJsonObject();
        LocalDateTime startDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("startDate").getAsString()), LocalTime.MIN);
        LocalDateTime endDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("endDate").getAsString()), LocalTime.MAX);
        return service.findBetweenDates(startDate, endDate)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/consultant/{consultantId}")
    @ApiOperation("List all Advisories by consultant id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<Set<Advisory>> findByConsultant(@PathVariable("consultantId") String consultantId) {
        return service.findByConsultant(consultantId)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/consultant/{consultantId}/between-dates")
    @ApiOperation("List all Advisories by consultant between two dates")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<Set<Advisory>> findByConsultantAndBetweenDates(
            @PathVariable("consultantId") String consultantId, String criteria
    ) {
        JsonObject jsonObject = JsonParser.parseString(criteria).getAsJsonObject();
        LocalDateTime startDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("startDate").getAsString()), LocalTime.MIN);
        LocalDateTime endDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("endDate").getAsString()), LocalTime.MAX);
        return service.findByConsultantAndBetweenDates(consultantId, startDate, endDate)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/client/{clientId}")
    @ApiOperation("List all Advisories by client id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<Set<Advisory>> findByClient(@PathVariable("clientId") String clientId) {
        return service.findByClient(clientId)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/consultant/{consultantId}/client/{clientId}")
    @ApiOperation("List all Advisories by consultant and client")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<Set<Advisory>> findByConsultantAndClient(@PathVariable("consultantId") String consultantId,
                                                                   @PathVariable("clientId") String clientId) {
        return service.findByConsultantAndClient(consultantId, clientId)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/consultant/{consultantId}/client/{clientId}/between-dates")
    @ApiOperation("List all Advisories by consultant and client between two dates")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<Set<Advisory>> findByConsultantAndClientBetweenDates(
            @PathVariable("consultantId") String consultantId,
            @PathVariable("clientId") String clientId,
            @RequestBody String criteria) {
        JsonObject jsonObject = JsonParser.parseString(criteria).getAsJsonObject();
        LocalDateTime startDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("startDate").getAsString()), LocalTime.MIN);
        LocalDateTime endDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("endDate").getAsString()), LocalTime.MAX);
        return service.findByConsultantAndClientBetweenDates(consultantId, clientId, startDate, endDate)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete an exiting Advisory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisory not found")
    })
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        return service.delete(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
