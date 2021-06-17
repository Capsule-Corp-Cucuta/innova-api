package co.edu.ufps.innova.authentication.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Data
@AllArgsConstructor
public class AuthenticationResponse {

    private String jwt;
    private Collection<? extends GrantedAuthority> authorities;

}
