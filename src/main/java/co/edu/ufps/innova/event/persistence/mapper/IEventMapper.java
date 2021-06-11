package co.edu.ufps.innova.event.persistence.mapper;

import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.InheritInverseConfiguration;
import co.edu.ufps.innova.event.domain.dto.Event;
import co.edu.ufps.innova.event.persistence.entity.EventEntity;
import co.edu.ufps.innova.inscription.persistence.mapper.IInscriptionMapper;

@Mapper(componentModel = "spring", uses = {IInscriptionMapper.class})
public interface IEventMapper {

    Event toEvent(EventEntity eventEntity);

    @InheritInverseConfiguration
    EventEntity toEventEntity(Event event);

    Set<Event> toEvents(Set<EventEntity> eventEntities);

    Set<EventEntity> toEventEntities(Set<Event> events);

}
