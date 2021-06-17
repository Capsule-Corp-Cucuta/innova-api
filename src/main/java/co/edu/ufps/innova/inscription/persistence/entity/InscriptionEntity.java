package co.edu.ufps.innova.inscription.persistence.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import co.edu.ufps.innova.event.persistence.entity.EventEntity;
import co.edu.ufps.innova.contact.persistence.entity.ContactEntity;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "inscriptions")
public class InscriptionEntity {

    @EmbeddedId
    private InscriptionEntityPK id;

    @Column(name = "inscription_date")
    private LocalDate inscriptionDate;

    private Boolean attended;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", insertable = false, updatable = false)
    private EventEntity event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private ContactEntity contact;

    public InscriptionEntity(Long eventId, String userId) {
        this.id = new InscriptionEntityPK(eventId, userId);
        this.inscriptionDate = LocalDate.now();
    }

}

