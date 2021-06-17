package co.edu.ufps.innova.authentication.domain.dto;

import lombok.Data;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Data
public class PasswordChange {

    private String oldPassword;
    private String newPassword;

}
