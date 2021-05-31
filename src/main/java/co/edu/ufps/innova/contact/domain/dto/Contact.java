package co.edu.ufps.innova.contact.domain.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import co.edu.ufps.innova.security.domain.dto.User;

@Data
@EqualsAndHashCode(callSuper = true)
public class Contact extends User implements Serializable {

    private ContactType type;
    private boolean requestAccompaniment;
    private LocalDateTime registrationDate;

    private String nit;
    private String companyName;
    private String companyDepartment;
    private String companyCity;
    private String companyAddress;
    private String companyEmail;
    private String companyPhone;
    private String companyCellphone;
    private String companyWebsite;

}
