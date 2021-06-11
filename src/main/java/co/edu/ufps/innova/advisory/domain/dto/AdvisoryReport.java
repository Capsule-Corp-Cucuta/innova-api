package co.edu.ufps.innova.advisory.domain.dto;

import lombok.Data;
import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import co.edu.ufps.innova.consultant.domain.dto.Consultant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvisoryReport implements Serializable {

    private Consultant consultant;
    private long AdvisoryHours;

}
