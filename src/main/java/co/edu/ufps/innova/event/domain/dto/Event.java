package co.edu.ufps.innova.event.domain.dto;

import lombok.Data;
import java.time.LocalDate;
import java.io.Serializable;

@Data
public class Event implements Serializable {

    private String title;
    private String theme;
    private String description;
    private LocalDate startDate;
    private LocalDate closeDate;
    private LocalDate registrationDeadline;
    private String eventTime;
    private byte eventDurationInHours;
    private EventType type;
    private EventState state;
    private String department;
    private String city;
    private String place;
    private String email;
    private String link;

}
