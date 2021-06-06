package co.edu.ufps.innova.consultant.domain.dto;

import lombok.*;
import java.io.Serializable;
import co.edu.ufps.innova.user.domain.dto.User;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Consultant extends User implements Serializable {

    private String code;

    public Consultant(String id, String name, String lastname, String email, String code) {
        super(id, name, lastname, email);
        this.code = code;
    }

}
