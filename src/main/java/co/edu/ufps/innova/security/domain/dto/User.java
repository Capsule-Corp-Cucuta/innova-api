package co.edu.ufps.innova.security.domain.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class User implements Serializable {

    private String id;
    private String name;
    private String lastname;
    private String email;
    private String cellphone;
    private String address;
    private boolean active;

}
