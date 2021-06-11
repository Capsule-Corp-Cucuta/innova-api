package co.edu.ufps.innova.inscription.persistence.mapper;

import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.InheritInverseConfiguration;
import co.edu.ufps.innova.inscription.domain.dto.Inscription;
import co.edu.ufps.innova.event.persistence.mapper.IEventMapper;
import co.edu.ufps.innova.contact.persistence.mapper.IContactMapper;
import co.edu.ufps.innova.inscription.persistence.entity.InscriptionEntity;

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

    Set<Inscription> toInscriptions(Set<InscriptionEntity> inscriptionEntities);

    Set<InscriptionEntity> toInscriptionEntities(Set<Inscription> inscriptions);

}
