package co.edu.ufps.innova.client.domain.mapper;

import org.mapstruct.*;
import co.edu.ufps.innova.client.domain.dto.Client;
import co.edu.ufps.innova.contact.domain.dto.Contact;
import co.edu.ufps.innova.user.persistence.mapper.IUserMapper;
import co.edu.ufps.innova.contact.persistence.mapper.IContactMapper;
import co.edu.ufps.innova.consultant.persistence.mapper.IConsultantMapper;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Mapper(componentModel = "spring", uses = {IUserMapper.class, IContactMapper.class, IConsultantMapper.class})
public interface IClientDomainMapper {

    @Mappings({
            @Mapping(target = "consultantId", ignore = true),
            @Mapping(target = "contactDepartment", ignore = true),
            @Mapping(target = "contactCity", ignore = true),
            @Mapping(target = "contactPhone", ignore = true),
            @Mapping(target = "birthplace", ignore = true),
            @Mapping(target = "birthdate", ignore = true),
            @Mapping(target = "gender", ignore = true),
            @Mapping(target = "ethnicGroup", ignore = true),
            @Mapping(target = "educationalLevel", ignore = true),
            @Mapping(target = "displaced", ignore = true),
            @Mapping(target = "handicapped", ignore = true),
            @Mapping(target = "positionInCompany", ignore = true),
            @Mapping(target = "dateOfEntryToCompany", ignore = true),
            @Mapping(target = "companyLegalRepresentative", ignore = true),
            @Mapping(target = "companyLegalConstitution", ignore = true),
            @Mapping(target = "otherLegalConstitution", ignore = true),
            @Mapping(target = "companyConstitutionDate", ignore = true),
            @Mapping(target = "companyNumberOfEmployees", ignore = true),
            @Mapping(target = "companyNumberOfFullTimeEmployees", ignore = true),
            @Mapping(target = "companyNumberOfPartTimeEmployees", ignore = true),
            @Mapping(target = "companyNumberOfDirectEmployees", ignore = true),
            @Mapping(target = "companyNumberOfIndirectEmployees", ignore = true),
            @Mapping(target = "companySector", ignore = true),
            @Mapping(target = "otherCompanySector", ignore = true),
            @Mapping(target = "hasCommercialRegister", ignore = true),
            @Mapping(target = "commercialRegisterNumber", ignore = true),
            @Mapping(target = "lastYearOfRenovation", ignore = true),
            @Mapping(target = "principalCodeCiiu", ignore = true),
            @Mapping(target = "internationalActivity", ignore = true),
            @Mapping(target = "internationalActivityCountries", ignore = true),
            @Mapping(target = "ecommerce", ignore = true),
            @Mapping(target = "servicesProductsOffered", ignore = true),
            @Mapping(target = "discoveryChannel", ignore = true),
            @Mapping(target = "observations", ignore = true),
    })
    Client toClient(Contact contact);

}
