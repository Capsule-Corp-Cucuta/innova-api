package co.edu.ufps.innova.event.domain.dto;

import lombok.*;
import java.time.LocalDate;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Event implements Serializable {

    private long id;
    private String title;
    private String theme;
    private String description;
    private EventType type;
    private EventState state;
    private LocalDate startDate;
    private LocalDate closeDate;
    private LocalDate registrationDeadline;
    private String eventTime;
    private byte eventDurationInHours;
    private String department;
    private String city;
    private String place;
    private String email;
    private String link;

    public Event(String title, String theme, String description, EventType type, EventState state,
                 LocalDate startDate, LocalDate registrationDeadline, String eventTime, String email) {
        this.title = title;
        this.theme = theme;
        this.description = description;
        this.type = type;
        this.state = state;
        this.startDate = startDate;
        this.registrationDeadline = registrationDeadline;
        this.eventTime = eventTime;
        this.email = email;
    }

}
