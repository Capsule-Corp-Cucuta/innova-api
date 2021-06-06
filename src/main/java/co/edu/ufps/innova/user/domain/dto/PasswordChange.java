package co.edu.ufps.innova.user.domain.dto;

import lombok.Data;

@Data
public class PasswordChange {

    private String oldPassword;
    private String newPassword;

}
