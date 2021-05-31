package co.edu.ufps.innova.consultant.domain.dto;

import lombok.Data;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import co.edu.ufps.innova.security.domain.dto.User;

@Data
@EqualsAndHashCode(callSuper = true)
public class Consultant extends User implements Serializable {

    private String code;

}
