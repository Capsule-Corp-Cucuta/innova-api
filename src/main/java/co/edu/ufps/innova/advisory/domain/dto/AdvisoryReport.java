package co.edu.ufps.innova.advisory.domain.dto;

import lombok.Data;
import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import co.edu.ufps.innova.consultant.domain.dto.Consultant;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvisoryReport implements Serializable {

    private Consultant consultant;
    private long AdvisoryHours;

}
