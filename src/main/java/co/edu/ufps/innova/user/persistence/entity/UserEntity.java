package co.edu.ufps.innova.user.persistence.entity;

import lombok.Data;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
@Table(name = "users")
@DiscriminatorValue("ADMIN")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "user_type")
public class UserEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String cellphone;

    private String address;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @Transient
    public String getUserType() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

}
