package co.edu.ufps.innova.user.domain.dto;

import lombok.Data;
import java.io.Serializable;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Data
@NoArgsConstructor
public class User implements Serializable {

    private String id;
    private String name;
    private String lastname;
    private String email;
    private String cellphone;
    private String address;
    private boolean active;

    public User(String id, String name, String lastname, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.active = true;
    }

}
