package co.edu.ufps.innova.inscription.persistence.reposiroty;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.ufps.innova.inscription.persistence.entity.InscriptionEntity;
import co.edu.ufps.innova.inscription.persistence.entity.InscriptionEntityPK;

public interface IInscriptionCrudRepository extends CrudRepository<InscriptionEntity, InscriptionEntityPK> {

    Optional<List<InscriptionEntity>> findByEventId(long eventId);

}
