package co.edu.ufps.innova.user.persistence.entity;

import lombok.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
@DiscriminatorValue("ADMININSTRADOR")
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

    @Column(unique = true)
    private String cellphone;

    private String address;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    public UserEntity(String id, String name, String lastname, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.active = true;
    }

    @Transient
    public String getUserType() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

}
