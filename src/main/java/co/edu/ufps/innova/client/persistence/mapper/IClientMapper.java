package co.edu.ufps.innova.client.persistence.mapper;

import java.util.Set;
import org.mapstruct.*;
import co.edu.ufps.innova.client.domain.dto.Client;
import co.edu.ufps.innova.client.persistence.entity.ClientEntity;
import co.edu.ufps.innova.user.persistence.mapper.IUserMapper;
import co.edu.ufps.innova.contact.persistence.mapper.IContactMapper;
import co.edu.ufps.innova.consultant.persistence.mapper.IConsultantMapper;

@Mapper(componentModel = "spring", uses = {IUserMapper.class, IContactMapper.class, IConsultantMapper.class})
public interface IClientMapper {

    @Mappings({
            @Mapping(source = "isDisplaced", target = "displaced"),
            @Mapping(source = "isHandicapped", target = "handicapped"),
            @Mapping(source = "isEcommerce", target = "ecommerce"),
    })
    Client toClient(ClientEntity clientEntity);

    @Mappings({
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "active", ignore = true),
            @Mapping(target = "userType", ignore = true),
            @Mapping(target = "inscriptions", ignore = true),
            @Mapping(target = "consultant", ignore = true),
            @Mapping(target = "advisories", ignore = true),
    })
    @InheritInverseConfiguration
    ClientEntity toClientEntity(Client client);

    Set<Client> toClientList(Set<ClientEntity> clientEntities);

}
