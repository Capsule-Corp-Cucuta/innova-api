package co.edu.ufps.innova.authentication.domain.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BetweenDates {

    private LocalDate startDate;
    private LocalDate endDate;

}
