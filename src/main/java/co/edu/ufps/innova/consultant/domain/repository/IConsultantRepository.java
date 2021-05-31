package co.edu.ufps.innova.consultant.domain.repository;

import java.util.List;
import java.util.Optional;
import co.edu.ufps.innova.consultant.domain.dto.Consultant;

public interface IConsultantRepository {

    Consultant save(Consultant consultant, String password);

    Optional<Consultant> findById(String id);

    Optional<Consultant> findByCode(String code);

    List<Consultant> findAll();

    void delete(Consultant consultant);

}
