package co.edu.ufps.innova.event.persistence.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.InheritInverseConfiguration;
import co.edu.ufps.innova.event.domain.dto.Event;
import co.edu.ufps.innova.event.persistence.entity.EventEntity;
import co.edu.ufps.innova.inscription.persistence.mapper.IInscriptionMapper;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Mapper(componentModel = "spring", uses = {IInscriptionMapper.class})
public interface IEventMapper {

    Event toEvent(EventEntity eventEntity);

    @InheritInverseConfiguration
    EventEntity toEventEntity(Event event);

    List<Event> toEvents(List<EventEntity> eventEntities);

    List<EventEntity> toEventEntities(List<Event> events);

}
