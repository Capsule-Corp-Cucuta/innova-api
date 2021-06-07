package co.edu.ufps.innova.advisory.web.controller;

import java.util.List;
import java.time.LocalDate;

import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.ufps.innova.advisory.domain.dto.Advisory;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryArea;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryType;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryState;
import co.edu.ufps.innova.authentication.domain.dto.BetweenDates;
import co.edu.ufps.innova.advisory.domain.service.AdvisoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/advisory")
public class AdvisoryController {

    private final AdvisoryService service;

    @PostMapping
    @ApiOperation("Save a new Advisory")
    @ApiResponse(code = 201, message = "Created")
    public ResponseEntity<Advisory> save(@RequestBody Advisory advisory) {
        return new ResponseEntity<>(service.save(advisory), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update an existing Advisory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisory not found")
    })
    public ResponseEntity<HttpStatus> update(@PathVariable("id") long id, @RequestBody Advisory advisory) {
        return service.update(id, advisory)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @ApiOperation("List all Advisories")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Advisory>> findAll() {
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

    @GetMapping("/consultant/{consultantId}")
    @ApiOperation("List all Advisories by consultant id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<List<Advisory>> findByConsultant(@PathVariable("consultantId") String consultantId) {
        return service.findByConsultant(consultantId)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/client/{clientId}")
    @ApiOperation("List all Advisories by client id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<List<Advisory>> findByClient(@PathVariable("clientId") String clientId) {
        return service.findByClient(clientId)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/type/{type}")
    @ApiOperation("List all Advisories by type")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<List<Advisory>> findByType(@PathVariable("type") AdvisoryType type) {
        return service.findByType(type)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/area/{area}")
    @ApiOperation("List all Advisories by area")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<List<Advisory>> findByArea(@PathVariable("area") AdvisoryArea area) {
        return service.findByArea(area)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/state/{state}")
    @ApiOperation("List all Advisories by state")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<List<Advisory>> findByState(@PathVariable("state") AdvisoryState state) {
        return service.findByState(state)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/date")
    @ApiOperation("List all Advisories by date")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<List<Advisory>> findByDate(@RequestBody LocalDate date) {
        return service.findByDate(date)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/between-dates")
    @ApiOperation("List all Advisories between two dates")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<List<Advisory>> findBetweenDates(@RequestBody BetweenDates dates) {
        return service.findBetweenDates(dates.getStartDate(), dates.getEndDate())
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/consultant/{consultantId}/client/{clientId}")
    @ApiOperation("List all Advisories by consultant and client")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<List<Advisory>> findByConsultantAndClient(@PathVariable("consultantID") String consultantId,
                                                                    @PathVariable("clientId") String clientId) {
        return service.findByConsultantAndClient(consultantId, clientId)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/consultant/{consultantId}/type/{type}")
    @ApiOperation("List all Advisories by consultant and type")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<List<Advisory>> findByConsultantAndType(@PathVariable("consultantId") String consultantId,
                                                                  @PathVariable("type") AdvisoryType type) {
        return service.findByConsultantAndType(consultantId, type)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/consultant/{consultantId}/area/{area}")
    @ApiOperation("List all Advisories by consultant and area")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<List<Advisory>> findByConsultantAndArea(@PathVariable("consultantId") String consultantId,
                                                                  @PathVariable("area") AdvisoryArea area) {
        return service.findByConsultantAndArea(consultantId, area)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/consultant/{consultantId}/state/{state}")
    @ApiOperation("List all Advisories by consultant and state")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<List<Advisory>> findByConsultantAndState(@PathVariable("consultantId") String consultantId,
                                                                   @PathVariable("state") AdvisoryState state) {
        return service.findByConsultantAndState(consultantId, state)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/consultant/{consultantId}/date")
    @ApiOperation("List all Advisories by consultant and date")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<List<Advisory>> findByConsultantAndDate(@PathVariable("consultantId") String consultantId,
                                                                  @RequestBody LocalDate date) {
        return service.findByConsultantAndDate(consultantId, date)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/consultant/{consultantId}/between-dates")
    @ApiOperation("List all Advisories by consultant between two dates")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<List<Advisory>> findByConsultantAndBetweenDates(@PathVariable("consultantId") String consultantId,
                                                                          @RequestBody BetweenDates dates) {
        return service.findByConsultantAndBetweenDates(consultantId, dates.getStartDate(), dates.getEndDate())
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/consultant/{consultantId}/client/{clientId}/date")
    @ApiOperation("List all Advisories by consultant and client and a date")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<List<Advisory>> findByConsultantAndClientAndDate(
            @PathVariable("consultantId") String consultantId,
            @PathVariable("clientId") String clientId,
            @RequestBody LocalDate date) {
        return service.findByConsultantAndClientAndDate(consultantId, clientId, date)
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/consultant/{consultantId}/client/{clientId}/between-dates")
    @ApiOperation("List all Advisories by consultant and client between two dates")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisories not found")
    })
    public ResponseEntity<List<Advisory>> findByConsultantAndClientBetweenDates(
            @PathVariable("consultantId") String consultantId,
            @PathVariable("clientId") String clientId,
            @RequestBody BetweenDates dates) {
        return service.findByConsultantAndClientBetweenDates(
                consultantId,
                clientId,
                dates.getStartDate(),
                dates.getEndDate())
                .map(advisories -> new ResponseEntity<>(advisories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/count")
    @ApiOperation("Count all Advisories")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(service.count(), HttpStatus.OK);
    }

    @GetMapping("/consultant/{consultantId}/count")
    @ApiOperation("Count all Advisories by consultant")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Long> countByConsultant(@PathVariable("consultantId") String consultantId) {
        return new ResponseEntity<>(service.countByConsultant(consultantId), HttpStatus.OK);
    }

    @GetMapping("/consultant/{consultantId}/between-dates/count")
    @ApiOperation("Count all Advisories by consultant between two dates")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Long> countByConsultantBetweenDates(@PathVariable("consultantId") String consultantId,
                                                              @RequestBody BetweenDates dates) {
        return new ResponseEntity<>(
                service.countByConsultantBetweenDates(consultantId, dates.getStartDate(), dates.getEndDate()),
                HttpStatus.OK
        );
    }

    @GetMapping("/consultant/{consultantId}/count-all-hours")
    @ApiOperation("Count all hours of Advisories by consultant with preparation time")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Long> countHoursByConsultantWithPreparation(
            @PathVariable("consultantId") String consultantId) {
        return new ResponseEntity<>(
                service.countHoursByConsultantWithPreparation(consultantId),
                HttpStatus.OK
        );
    }

    @GetMapping("/consultant/{consultantId}/count-advisory-hours")
    @ApiOperation("Count all hours of Advisories by consultant without preparation time")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Long> countHoursByConsultantWithoutPreparation(
            @PathVariable("consultantId") String consultantId) {
        return new ResponseEntity<>(
                service.countHoursByConsultantWithoutPreparation(consultantId),
                HttpStatus.OK
        );
    }

    @GetMapping("/consultant/{consultantId}/count-advisory-hours/between-dates")
    @ApiOperation("Count all hours of Advisories by consultant without preparation time between two dates")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Long> countHoursByConsultantWithoutPreparationBetweenDates(
            @PathVariable("consultantId") String consultantId,
            @RequestBody BetweenDates dates) {
        return new ResponseEntity<>(
                service.countHoursByConsultantWithoutPreparationBetweenDates(
                        consultantId,
                        dates.getStartDate(),
                        dates.getEndDate()
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete an exiting Advisory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Advisory not found")
    })
    public ResponseEntity<HttpStatus> delete(long id) {
        return service.delete(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
