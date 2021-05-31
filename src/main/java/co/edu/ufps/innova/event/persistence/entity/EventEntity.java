package co.edu.ufps.innova.event.persistence.entity;

import lombok.Data;
import java.util.List;
import javax.persistence.*;
import java.time.LocalDate;
import co.edu.ufps.innova.event.domain.dto.EventType;
import co.edu.ufps.innova.event.domain.dto.EventState;
import co.edu.ufps.innova.inscription.persistence.entity.InscriptionEntity;

@Data
@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String theme;

    @Column(nullable = false)
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "close_date")
    private LocalDate closeDate;

    @Column(nullable = false)
    private LocalDate registrationDeadline;

    @Column(name = "event_time", nullable = false)
    private String eventTime;

    @Column(name = "event_duration_in_hours")
    private Byte eventDurationInHours;

    @Column(nullable = false)
    private EventType type;

    @Column(nullable = false)
    private EventState state;

    private String department;

    private String city;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String link;

    @OneToMany(mappedBy = "event")
    private List<InscriptionEntity> inscriptions;

}
