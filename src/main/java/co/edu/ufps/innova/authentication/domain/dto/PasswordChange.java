package co.edu.ufps.innova.authentication.domain.dto;

import lombok.Data;

@Data
public class PasswordChange {

    private String oldPassword;
    private String newPassword;

}
