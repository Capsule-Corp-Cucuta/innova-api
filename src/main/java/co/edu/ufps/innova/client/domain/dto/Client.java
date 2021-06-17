package co.edu.ufps.innova.client.domain.dto;

import lombok.*;
import java.time.LocalDate;
import java.io.Serializable;
import co.edu.ufps.innova.contact.domain.dto.Contact;
import co.edu.ufps.innova.contact.domain.dto.ContactType;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Client extends Contact implements Serializable {

    private String contactDepartment;
    private String contactCity;
    private String contactPhone;
    private String birthplace;
    private LocalDate birthdate;
    private Gender gender;
    private EthnicGroup ethnicGroup;
    private EducationalLevel educationalLevel;
    private boolean isDisplaced;
    private boolean isHandicapped;
    private String positionInCompany;
    private LocalDate dateOfEntryToCompany;
    private String companyLegalRepresentative;
    private LegalConstitution companyLegalConstitution;
    private String otherLegalConstitution;
    private LocalDate companyConstitutionDate;
    private short companyNumberOfEmployees;
    private short companyNumberOfFullTimeEmployees;
    private short companyNumberOfPartTimeEmployees;
    private short companyNumberOfDirectEmployees;
    private short companyNumberOfIndirectEmployees;
    private CompanySector companySector;
    private String otherCompanySector;
    private boolean hasCommercialRegister;
    private String commercialRegisterNumber;
    private LocalDate lastYearOfRenovation;
    private String principalCodeCiiu;
    private InternationalActivity internationalActivity;
    private String internationalActivityCountries;
    private boolean isEcommerce;
    private String servicesProductsOffered;
    private DiscoveryChannel discoveryChannel;
    private String observations;
    private String consultantId;

    public Client(String id, String name, String lastname, String email, ContactType type, boolean requestAccompaniment, String consultantId) {
        super(id, name, lastname, email, type, requestAccompaniment);
        this.consultantId = consultantId;
    }

}
