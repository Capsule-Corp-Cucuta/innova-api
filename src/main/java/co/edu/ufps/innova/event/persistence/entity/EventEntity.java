package co.edu.ufps.innova.event.persistence.entity;

import lombok.*;
import java.util.List;
import javax.persistence.*;
import java.time.LocalDate;
import co.edu.ufps.innova.event.domain.dto.EventType;
import co.edu.ufps.innova.event.domain.dto.EventState;
import co.edu.ufps.innova.inscription.persistence.entity.InscriptionEntity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String theme;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private EventType type;

    @Column(nullable = false)
    private EventState state;

    @Column(nullable = false, name = "start_date")
    private LocalDate startDate;

    @Column(name = "close_date")
    private LocalDate closeDate;

    @Column(nullable = false)
    private LocalDate registrationDeadline;

    @Column(name = "event_time", nullable = false)
    private String eventTime;

    @Column(name = "event_duration_in_hours")
    private Byte eventDurationInHours;

    private String department;

    private String city;

    private String place;

    @Column(nullable = false)
    private String email;

    private String link;

    @OneToMany(mappedBy = "event")
    private List<InscriptionEntity> inscriptions;

    public EventEntity(String title, String theme, String description, EventType type, EventState state,
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
