package co.edu.ufps.innova.event.persistence.entity;

import lombok.*;
import java.util.List;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime startDate;

    @Column(nullable = false, name = "close_date")
    private LocalDateTime closeDate;

    @Column(nullable = false, name = "registration_dead_line_date")
    private LocalDate registrationDeadlineDate;

    @Column(name = "event_duration_in_hours")
    private Byte eventDurationInHours;

    private String department;

    private String city;

    private String place;

    @Column(nullable = false)
    private String email;

    private String link;

    @OneToMany(mappedBy = "event", orphanRemoval = true, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<InscriptionEntity> inscriptions;

    public EventEntity(String title, String theme, String description, EventType type, EventState state,
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
