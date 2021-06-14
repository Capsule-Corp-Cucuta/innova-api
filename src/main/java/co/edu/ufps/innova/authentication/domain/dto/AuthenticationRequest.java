package co.edu.ufps.innova.authentication.domain.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class AuthenticationRequest {

    private String email;
    private String password;

}
