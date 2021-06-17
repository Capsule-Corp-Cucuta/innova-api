package co.edu.ufps.innova.contact.domain.dto;

import lombok.*;
import java.time.LocalDate;
import java.io.Serializable;
import co.edu.ufps.innova.user.domain.dto.User;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Contact extends User implements Serializable {

    private ContactType type;
    private boolean requestAccompaniment;
    private LocalDate registrationDate;
    private String nit;
    private String companyName;
    private String companyDepartment;
    private String companyCity;
    private String companyAddress;
    private String companyEmail;
    private String companyPhone;
    private String companyCellphone;
    private String companyWebsite;

    public Contact(String id, String name, String lastname, String email, ContactType type, boolean requestAccompaniment) {
        super(id, name, lastname, email);
        this.type = type;
        this.requestAccompaniment = requestAccompaniment;
        this.registrationDate = LocalDate.now();
    }

}
