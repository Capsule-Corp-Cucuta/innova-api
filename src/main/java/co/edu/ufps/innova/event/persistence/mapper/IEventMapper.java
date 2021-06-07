package co.edu.ufps.innova.event.persistence.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.InheritInverseConfiguration;
import co.edu.ufps.innova.event.domain.dto.Event;
import co.edu.ufps.innova.event.persistence.entity.EventEntity;
import co.edu.ufps.innova.contact.persistence.mapper.IContactMapper;

@Mapper(componentModel = "spring", uses = {IContactMapper.class})
public interface IEventMapper {

    Event toEvent(EventEntity eventEntity);

    @Mappings({
            @Mapping(target = "inscriptions", ignore = true),
    })
    @InheritInverseConfiguration
    EventEntity toEventEntity(Event event);

    List<Event> toEvents(List<EventEntity> eventEntities);

}
