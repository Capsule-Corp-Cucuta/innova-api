package co.edu.ufps.innova.consultant.persistence.entity;

import lombok.*;
import java.util.List;
import javax.persistence.*;
import co.edu.ufps.innova.user.persistence.entity.UserEntity;
import co.edu.ufps.innova.client.persistence.entity.ClientEntity;
import co.edu.ufps.innova.advisory.persistence.entity.AdvisoryEntity;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Data
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "consultants")
@DiscriminatorValue("ASESOR")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class ConsultantEntity extends UserEntity {

    @Column(nullable = false, unique = true)
    private String code;

    @OneToMany(mappedBy = "consultant", fetch = FetchType.LAZY)
    private List<ClientEntity> clients;

    @OneToMany(mappedBy = "consultant", fetch = FetchType.LAZY)
    private List<AdvisoryEntity> advisories;

    public ConsultantEntity(String id, String name, String lastname, String email, String code) {
        super(id, name, lastname, email);
        this.code = code;
    }

}
