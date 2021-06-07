package co.edu.ufps.innova.advisory.persistence.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryArea;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryType;
import co.edu.ufps.innova.advisory.domain.dto.AdvisoryState;
import co.edu.ufps.innova.client.persistence.entity.ClientEntity;
import co.edu.ufps.innova.consultant.persistence.entity.ConsultantEntity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "advisories")
public class AdvisoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private AdvisoryType type;

    @Column(nullable = false)
    private AdvisoryArea area;

    @Column(nullable = false)
    private AdvisoryState state;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(name = "duration_in_hours")
    private Byte durationInHours;

    @Column(name = "preparation_type_in_hours")
    private Byte preparationTypeInHours;

    @Lob
    private String notes;

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

    public AdvisoryEntity(String clientId, String consultantId, String subject, AdvisoryType type, AdvisoryArea area,
                          AdvisoryState state, LocalDateTime date) {
        this.clientId = clientId;
        this.consultantId = consultantId;
        this.subject = subject;
        this.type = type;
        this.area = area;
        this.state = state;
        this.date = date;
    }

}
