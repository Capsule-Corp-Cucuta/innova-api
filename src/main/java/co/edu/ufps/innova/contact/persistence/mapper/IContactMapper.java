package co.edu.ufps.innova.contact.persistence.mapper;

import java.util.List;
import org.mapstruct.*;
import co.edu.ufps.innova.contact.domain.dto.Contact;
import co.edu.ufps.innova.security.persistence.mapper.IUserMapper;
import co.edu.ufps.innova.contact.persistence.entity.ContactEntity;

@Mapper(componentModel = "spring", uses = {IUserMapper.class})
public interface IContactMapper {

    Contact toContact(ContactEntity entity);

    @Mappings({
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "active", ignore = true),
            @Mapping(target = "userType", ignore = true),
            @Mapping(target = "inscriptions", ignore = true),
    })
    @InheritInverseConfiguration
    ContactEntity toContactEntity(Contact contact);

    List<Contact> toContactList(List<ContactEntity> contactEntities);

}
