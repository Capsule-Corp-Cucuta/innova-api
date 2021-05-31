package co.edu.ufps.innova.client.persistence.mapper;

import java.util.List;
import org.mapstruct.*;
import co.edu.ufps.innova.client.domain.dto.Client;
import co.edu.ufps.innova.client.persistence.entity.ClientEntity;
import co.edu.ufps.innova.security.persistence.mapper.IUserMapper;
import co.edu.ufps.innova.contact.persistence.mapper.IContactMapper;
import co.edu.ufps.innova.consultant.persistence.mapper.IConsultantMapper;

@Mapper(componentModel = "spring", uses = {IUserMapper.class, IContactMapper.class, IConsultantMapper.class})
public interface IClientMapper {

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

    List<Client> toClientList(List<ClientEntity> clientEntities);

}
