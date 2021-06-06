package co.edu.ufps.innova.contact.persistence.entity;

import lombok.*;
import java.util.List;
import java.time.LocalDate;
import javax.persistence.*;
import co.edu.ufps.innova.contact.domain.dto.ContactType;
import co.edu.ufps.innova.user.persistence.entity.UserEntity;
import co.edu.ufps.innova.inscription.persistence.entity.InscriptionEntity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "contacts")
@ToString(callSuper = true)
@DiscriminatorValue("CONTACT")
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class ContactEntity extends UserEntity {

    @Column(name = "user_type")
    private String userType;

    @Column(name = "contact_type", nullable = false)
    private ContactType type;

    @Column(name = "request_accompaniment", nullable = false)
    private Boolean requestAccompaniment;

    @Column(nullable = false)
    private LocalDate registrationDate;

    private String nit;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_department")
    private String companyDepartment;

    @Column(name = "company_city")
    private String companyCity;

    @Column(name = "company_address")
    private String companyAddress;

    @Column(name = "company_email")
    private String companyEmail;

    @Column(name = "company_phone")
    private String companyPhone;

    @Column(name = "company_cellphone")
    private String companyCellphone;

    @Column(name = "company_website")
    private String companyWebsite;

    @OneToMany(mappedBy = "contact")
    private List<InscriptionEntity> inscriptions;

    public ContactEntity(String id, String name, String lastname, String email, ContactType type, boolean requestAccompaniment) {
        super(id, name, lastname, email);
        this.type = type;
        this.requestAccompaniment = requestAccompaniment;
        this.registrationDate = LocalDate.now();
    }

}
