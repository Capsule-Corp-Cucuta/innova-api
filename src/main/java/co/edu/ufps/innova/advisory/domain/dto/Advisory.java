package co.edu.ufps.innova.advisory.domain.dto;

import lombok.Data;
import java.time.LocalDate;
import java.io.Serializable;

@Data
public class Advisory implements Serializable {

    private String clientId;
    private String consultantId;
    private LocalDate date;
    private AdvisoryType type;
    private String subject;
    private Byte durationInHours;
    private Byte preparationTypeInHours;
    private AdvisoryArea area;
    private String notes;
    private AdvisoryState state;

}
