package co.edu.ufps.innova.inscription.domain.dto;

import lombok.*;
import java.time.LocalDate;
import java.io.Serializable;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Data
@NoArgsConstructor
public class Inscription implements Serializable {

    private long eventId;
    private String userId;
    private LocalDate inscriptionDate;
    private boolean attended;

    public Inscription(long eventId, String userId) {
        this.userId = userId;
        this.eventId = eventId;
        this.inscriptionDate = LocalDate.now();
    }

}
