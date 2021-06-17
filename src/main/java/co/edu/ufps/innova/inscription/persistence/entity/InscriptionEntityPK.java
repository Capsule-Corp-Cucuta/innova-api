package co.edu.ufps.innova.inscription.persistence.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionEntityPK implements Serializable {

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "user_id", nullable = false)
    private String userId;

}
