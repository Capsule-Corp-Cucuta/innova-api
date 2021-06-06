package co.edu.ufps.innova.inscription.persistence.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
public class InscriptionEntityPK implements Serializable {

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "user_id", nullable = false)
    private String userId;

}
