package co.edu.ufps.innova.client.persistence.entity;

import lombok.Data;
import java.util.List;
import javax.persistence.*;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import co.edu.ufps.innova.client.domain.dto.*;
import co.edu.ufps.innova.contact.persistence.entity.ContactEntity;
import co.edu.ufps.innova.advisory.persistence.entity.AdvisoryEntity;
import co.edu.ufps.innova.consultant.persistence.entity.ConsultantEntity;

@Data
@Entity
@Table(name = "clients")
@DiscriminatorValue("CLIENT")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class ClientEntity extends ContactEntity {

    @Column(name = "contact_department")
    private String contactDepartment;

    @Column(name = "contact_city")
    private String contactCity;

    @Column(name = "contact_phone", unique = true)
    private String contactPhone;

    private String birthplace;

    private LocalDate birthdate;

    private Gender gender;

    @Column(name = "ethnic_group")
    private EthnicGroup ethnicGroup;

    @Column(name = "educational_level")
    private EducationalLevel educationalLevel;

    @Column(name = "is_displaced")
    private Boolean isDisplaced;

    @Column(name = "is_handicapped")
    private Boolean isHandicapped;

    @Column(name = "position_in_company")
    private String positionInCompany;

    @Column(name = "date_of_entry_company")
    private LocalDate dateOfEntryToCompany;

    @Column(name = "company_legal_representative")
    private String companyLegalRepresentative;

    @Column(name = "company_legal_constitution")
    private LegalConstitution companyLegalConstitution;

    @Column(name = "other_legal_constitution")
    private String otherLegalConstitution;

    @Column(name = "company_constitution_date")
    private LocalDate companyConstitutionDate;

    @Column(name = "company_number_of_employees")
    private Short companyNumberOfEmployees;

    @Column(name = "company_number_of_full_time_employees")
    private Short companyNumberOfFullTimeEmployees;

    @Column(name = "company_number_of_part_time_employees")
    private Short companyNumberOfPartTimeEmployees;

    @Column(name = "company_number_of_direct_employees")
    private Short companyNumberOfDirectEmployees;

    @Column(name = "company_number_of_indirect_employees")
    private Short companyNumberOfIndirectEmployees;

    @Column(name = "company_sector")
    private CompanySector companySector;

    @Column(name = "other_company_sector")
    private String otherCompanySector;

    @Column(name = "has_commercial_register")
    private Boolean hasCommercialRegister;

    @Column(name = "commercial_register_number")
    private String commercialRegisterNumber;

    @Column(name = "last_year_of_renovation")
    private LocalDate lastYearOfRenovation;

    @Column(name = "principal_code_ciiu")
    private String principalCodeCiiu;

    @Column(name = "international_activity")
    private InternationalActivity internationalActivity;

    @Column(name = "international_activity_countries")
    private String internationalActivityCountries;

    @Column(name = "is_ecommerce")
    private Boolean isEcommerce;

    @Lob
    @Column(name = "services_products_offered")
    private String servicesProductsOffered;

    @Column(name = "discovery_channel")
    private DiscoveryChannel discoveryChannel;

    @Lob
    private String observations;

    @Column(name = "consultant_id", nullable = false)
    private String consultantId;

    @ManyToOne
    @JoinColumn(name = "consultant_id", insertable = false, updatable = false)
    private ConsultantEntity consultant;

    @OneToMany(mappedBy = "client")
    private List<AdvisoryEntity> advisories;

}


