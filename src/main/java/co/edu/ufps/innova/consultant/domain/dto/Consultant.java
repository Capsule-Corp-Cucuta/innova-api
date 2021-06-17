package co.edu.ufps.innova.consultant.domain.dto;

import lombok.*;
import java.io.Serializable;
import co.edu.ufps.innova.user.domain.dto.User;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
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
