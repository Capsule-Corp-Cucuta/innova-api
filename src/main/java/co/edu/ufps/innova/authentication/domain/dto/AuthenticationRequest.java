package co.edu.ufps.innova.authentication.domain.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Data
@AllArgsConstructor
public class AuthenticationRequest {

    private String email;
    private String password;

}
