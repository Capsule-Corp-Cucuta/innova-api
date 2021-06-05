package co.edu.ufps.innova.authentication.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

    private String jwt;
    private Collection<? extends GrantedAuthority> authorities;

}
