package co.edu.ufps.innova.advisory.domain.dto;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Advisory implements Serializable {

    private long id;
    private String consultantId;
    private String clientId;
    private String subject;
    private AdvisoryType type;
    private AdvisoryArea area;
    private AdvisoryState state;
    private LocalDateTime date;
    private byte durationInHours;
    private byte preparationTypeInHours;
    private String notes;

    public Advisory(String clientId, String consultantId, String subject, AdvisoryType type, AdvisoryArea area,
                    AdvisoryState state, LocalDateTime date) {
        this.clientId = clientId;
        this.consultantId = consultantId;
        this.subject = subject;
        this.type = type;
        this.area = area;
        this.state = state;
        this.date = date;
    }

}
