package co.edu.ufps.innova.consultant.persistence.mapper;

import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.InheritInverseConfiguration;
import co.edu.ufps.innova.consultant.domain.dto.Consultant;
import co.edu.ufps.innova.user.persistence.mapper.IUserMapper;
import co.edu.ufps.innova.consultant.persistence.entity.ConsultantEntity;

@Mapper(componentModel = "spring", uses = {IUserMapper.class})
public interface IConsultantMapper {

    Consultant toConsultant(ConsultantEntity consultantEntity);

    @Mappings({
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "active", ignore = true),
            @Mapping(target = "clients", ignore = true),
            @Mapping(target = "advisories", ignore = true),
    })
    @InheritInverseConfiguration
    ConsultantEntity toConsultantEntity(Consultant consultant);

    Set<Consultant> toConsultantList(Set<ConsultantEntity> consultantEntities);

}
