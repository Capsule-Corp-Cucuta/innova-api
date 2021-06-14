package co.edu.ufps.innova.advisory.persistence.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.InheritInverseConfiguration;
import co.edu.ufps.innova.advisory.domain.dto.Advisory;
import co.edu.ufps.innova.user.persistence.mapper.IUserMapper;
import co.edu.ufps.innova.client.persistence.mapper.IClientMapper;
import co.edu.ufps.innova.contact.persistence.mapper.IContactMapper;
import co.edu.ufps.innova.advisory.persistence.entity.AdvisoryEntity;
import co.edu.ufps.innova.consultant.persistence.mapper.IConsultantMapper;

@Mapper(componentModel = "spring", uses = {IUserMapper.class, IConsultantMapper.class, IContactMapper.class, IClientMapper.class})
public interface IAdvisoryMapper {

    Advisory toAdvisory(AdvisoryEntity advisoryEntity);

    @Mappings({
            @Mapping(target = "consultant", ignore = true),
            @Mapping(target = "client", ignore = true),
    })
    @InheritInverseConfiguration
    AdvisoryEntity toAdvisoryEntity(Advisory advisory);

    List<Advisory> toAdvisories(List<AdvisoryEntity> advisoryEntities);

}
