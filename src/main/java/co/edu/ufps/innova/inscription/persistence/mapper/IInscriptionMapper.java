package co.edu.ufps.innova.inscription.persistence.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.InheritInverseConfiguration;
import co.edu.ufps.innova.inscription.domain.dto.Inscription;
import co.edu.ufps.innova.event.persistence.mapper.IEventMapper;
import co.edu.ufps.innova.contact.persistence.mapper.IContactMapper;
import co.edu.ufps.innova.inscription.persistence.entity.InscriptionEntity;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {IEventMapper.class, IContactMapper.class})
public interface IInscriptionMapper {

    @Mappings({
            @Mapping(source = "id.eventId", target = "eventId"),
            @Mapping(source = "id.userId", target = "userId")
    })
    Inscription toInscription(InscriptionEntity inscriptionEntity);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "event", ignore = true),
            @Mapping(target = "contact", ignore = true),
    })
    InscriptionEntity toInscriptionEntity(Inscription inscription);

    List<Inscription> toInscriptions(List<InscriptionEntity> inscriptionEntities);

    List<InscriptionEntity> toInscriptionEntities(List<Inscription> inscriptions);

}
