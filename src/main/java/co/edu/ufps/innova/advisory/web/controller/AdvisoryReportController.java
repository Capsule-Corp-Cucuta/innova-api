package co.edu.ufps.innova.advisory.web.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryReport;
import co.edu.ufps.innova.advisory.domain.service.IAdvisoryReportService;

@RestController
@Api(tags = "advisory-report")
@RequiredArgsConstructor
@RequestMapping("/advisory-report")
public class AdvisoryReportController {

    private final IAdvisoryReportService service;

    @GetMapping
    @ApiOperation("Get a general report of hours of advisories by consultants")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<AdvisoryReport>> getGeneralReport() {
        List<AdvisoryReport> advisoryReports = service.getGeneralReport();
        return !advisoryReports.isEmpty()
                ? new ResponseEntity<>(advisoryReports, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/between-dates")
    @ApiOperation("Get a general report of hours of advisories by consultants between dates")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<AdvisoryReport>> getGeneralReportBetweenDates(String criteria) {
        JsonObject jsonObject = JsonParser.parseString(criteria).getAsJsonObject();
        LocalDateTime startDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("startDate").getAsString()), LocalTime.MIN);
        LocalDateTime endDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("endDate").getAsString()), LocalTime.MAX);
        List<AdvisoryReport> advisoryReports = service.getGeneralReportBetweenDates(startDate, endDate);
        return !advisoryReports.isEmpty()
                ? new ResponseEntity<>(advisoryReports, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/count-hours/consultant/{consultantId}")
    @ApiOperation("Count all hours of Advisories without preparation time by consultant")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Long> countHoursByConsultantWithoutPreparation(
            @PathVariable("consultantId") String consultantId) {
        return new ResponseEntity<>(
                service.countHoursByConsultant(consultantId),
                HttpStatus.OK
        );
    }

    @GetMapping("/count-hours/consultant/{consultantId}/between-dates")
    @ApiOperation("Count all hours without preparation time of Advisories by consultant between two dates")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Long> countHoursByConsultantWithoutPreparationBetweenDates(
            @PathVariable("consultantId") String consultantId, String criteria
    ) {
        JsonObject jsonObject = JsonParser.parseString(criteria).getAsJsonObject();
        LocalDateTime startDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("startDate").getAsString()), LocalTime.MIN);
        LocalDateTime endDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("endDate").getAsString()), LocalTime.MAX);
        return new ResponseEntity<>(
                service.countHoursByConsultantBetweenDates(consultantId, startDate, endDate),
                HttpStatus.OK
        );
    }

    @GetMapping("/with-preparation-time")
    @ApiOperation("Get a general report of hours with preparation time of advisories by consultants")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<AdvisoryReport>> getGeneralReportWithPreparationTime() {
        List<AdvisoryReport> advisoryReports = service.getGeneralReportWithPreparationTime();
        return !advisoryReports.isEmpty()
                ? new ResponseEntity<>(advisoryReports, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/with-preparation-time/between-dates")
    @ApiOperation("Get a general report of hours with preparation time of advisories by consultants")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<AdvisoryReport>> getGeneralReportWithPreparationTimeBetweenDates(String criteria) {
        JsonObject jsonObject = JsonParser.parseString(criteria).getAsJsonObject();
        LocalDateTime startDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("startDate").getAsString()), LocalTime.MIN);
        LocalDateTime endDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("endDate").getAsString()), LocalTime.MAX);
        List<AdvisoryReport> advisoryReports = service.getGeneralReportWithPreparationTimeBetweenDates(startDate, endDate);
        return !advisoryReports.isEmpty()
                ? new ResponseEntity<>(advisoryReports, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/count-hours/with-preparation-time/consultant/{consultantId}")
    @ApiOperation("Count all hours with preparation time of Advisories by consultant")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Long> countHoursByConsultantWithPreparationTime(
            @PathVariable("consultantId") String consultantId) {
        return new ResponseEntity<>(
                service.countHoursByConsultantWithPreparationTime(consultantId),
                HttpStatus.OK
        );
    }

    @GetMapping("/count-hours/with-preparation-time/consultant/{consultantId}/between-dates")
    @ApiOperation("Count all hours of Advisories by consultant without preparation time between two dates")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Long> countHoursByConsultantWithPreparationTimeBetweenDates(
            @PathVariable("consultantId") String consultantId, String criteria
    ) {
        JsonObject jsonObject = JsonParser.parseString(criteria).getAsJsonObject();
        LocalDateTime startDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("startDate").getAsString()), LocalTime.MIN);
        LocalDateTime endDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("endDate").getAsString()), LocalTime.MAX);
        return new ResponseEntity<>(
                service.countHoursByConsultantWithPreparationTimeBetweenDates(consultantId, startDate, endDate),
                HttpStatus.OK
        );
    }

    @GetMapping("/count-advisories/consultant/{consultantId}/between-dates")
    @ApiOperation("Count all Advisories by consultant between two dates")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Long> countAdvisoriesByConsultantBetweenDates(@PathVariable("consultantId") String consultantId,
                                                                        String criteria) {
        JsonObject jsonObject = JsonParser.parseString(criteria).getAsJsonObject();
        LocalDateTime startDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("startDate").getAsString()), LocalTime.MIN);
        LocalDateTime endDate = LocalDateTime.of(LocalDate.parse(jsonObject.get("endDate").getAsString()), LocalTime.MAX);
        return new ResponseEntity<>(
                service.countAdvisoriesByConsultantBetweenDates(consultantId, startDate, endDate), HttpStatus.OK
        );
    }

}
