package co.edu.ufps.innova.inscription.domain.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Inscription implements Serializable {

    private String eventId;
    private String userId;
    private LocalDateTime inscriptionDate;
    private boolean attended;

}
