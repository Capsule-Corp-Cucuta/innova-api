package co.edu.ufps.innova.consultant.persistence.entity;

import lombok.*;
import java.util.List;
import javax.persistence.*;
import co.edu.ufps.innova.user.persistence.entity.UserEntity;
import co.edu.ufps.innova.client.persistence.entity.ClientEntity;
import co.edu.ufps.innova.advisory.persistence.entity.AdvisoryEntity;

@Data
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "consultants")
@DiscriminatorValue("CONSULTANT")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class ConsultantEntity extends UserEntity {

    @Column(nullable = false, unique = true)
    private String code;

    @OneToMany(mappedBy = "consultant")
    private List<ClientEntity> clients;

    @OneToMany(mappedBy = "consultant")
    private List<AdvisoryEntity> advisories;

    public ConsultantEntity(String id, String name, String lastname, String email, String code) {
        super(id, name, lastname, email);
        this.code = code;
    }

}
