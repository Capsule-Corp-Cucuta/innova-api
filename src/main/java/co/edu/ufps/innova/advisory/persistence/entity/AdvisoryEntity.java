package co.edu.ufps.innova.advisory.persistence.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryArea;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryType;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryState;
import co.edu.ufps.innova.client.persistence.entity.ClientEntity;
import co.edu.ufps.innova.consultant.persistence.entity.ConsultantEntity;

@Data
@Entity
@Table(name = "advisories")
public class AdvisoryEntity {

    @Id
    private Long id;

    private LocalDate date;

    @Column(nullable = false)
    private AdvisoryType type;

    private String subject;

    @Column(name = "duration_in_hours")
    private Byte durationInHours;

    @Column(name = "preparation_type_in_hours")
    private Byte preparationTypeInHours;

    @Column(nullable = false)
    private AdvisoryArea area;

    @Lob
    private String notes;

    @Column(nullable = false)
    private AdvisoryState state;

    @Column(name = "consultant_id", nullable = false)
    private String consultantId;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @ManyToOne
    @JoinColumn(name = "consultant_id", insertable = false, updatable = false)
    private ConsultantEntity consultant;

    @ManyToOne
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private ClientEntity client;

}
