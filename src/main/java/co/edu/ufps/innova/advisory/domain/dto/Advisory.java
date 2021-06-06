package co.edu.ufps.innova.advisory.domain.dto;

import lombok.*;
import java.time.LocalDate;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Advisory implements Serializable {

    private String clientId;
    private String consultantId;
    private String subject;
    private AdvisoryType type;
    private AdvisoryArea area;
    private AdvisoryState state;
    private LocalDate date;
    private byte durationInHours;
    private byte preparationTypeInHours;
    private String notes;

    public Advisory(String clientId, String consultantId, String subject, AdvisoryType type, AdvisoryArea area, AdvisoryState state) {
        this.clientId = clientId;
        this.consultantId = consultantId;
        this.subject = subject;
        this.type = type;
        this.area = area;
        this.state = state;
        this.date = LocalDate.now();
    }

}
