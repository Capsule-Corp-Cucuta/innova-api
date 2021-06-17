package co.edu.ufps.innova.event.domain.dto;

import lombok.*;
import java.util.List;
import java.time.LocalDate;
import java.io.Serializable;
import java.time.LocalDateTime;
import co.edu.ufps.innova.inscription.domain.dto.Inscription;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Data
@NoArgsConstructor
public class Event implements Serializable {

    private long id;
    private String title;
    private String theme;
    private String description;
    private EventType type;
    private EventState state;
    private LocalDateTime startDate;
    private LocalDateTime closeDate;
    private LocalDate registrationDeadlineDate;
    private byte eventDurationInHours;
    private String department;
    private String city;
    private String place;
    private String email;
    private String link;
    private List<Inscription> inscriptions;

    public Event(String title, String theme, String description, EventType type, EventState state,
                 LocalDateTime startDate, LocalDateTime closeDate, LocalDate registrationDeadlineDate, String email) {
        this.title = title;
        this.theme = theme;
        this.description = description;
        this.type = type;
        this.state = state;
        this.startDate = startDate;
        this.closeDate = closeDate;
        this.registrationDeadlineDate = registrationDeadlineDate;
        this.email = email;
    }

}
