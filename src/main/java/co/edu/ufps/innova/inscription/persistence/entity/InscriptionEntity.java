package co.edu.ufps.innova.inscription.persistence.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import co.edu.ufps.innova.event.persistence.entity.EventEntity;
import co.edu.ufps.innova.contact.persistence.entity.ContactEntity;

@Data
@Entity
@Table(name = "inscriptions")
public class InscriptionEntity {

    @EmbeddedId
    private InscriptionEntityPK id;

    @Column(name = "inscription_date")
    private LocalDateTime inscriptionDate;

    private Boolean attended;

    @ManyToOne
    @JoinColumn(name = "event_id", insertable = false, updatable = false)
    private EventEntity event;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private ContactEntity contact;

}

