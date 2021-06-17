package co.edu.ufps.innova.user.persistence.mapper;

import java.util.List;
import org.mapstruct.*;
import co.edu.ufps.innova.user.domain.dto.User;
import co.edu.ufps.innova.user.persistence.entity.UserEntity;

/**
 * @author <a href="mailto:sergioandresrr@ufps.edu.co">Sergio Rodríguez</a>
 * @version 1.0.0
 * @since 2021
 */
@Mapper(componentModel = "spring")
public interface IUserMapper {

    User toUser(UserEntity userEntity);

    @Mappings({
            @Mapping(target = "password", ignore = true),
    })
    @InheritInverseConfiguration
    UserEntity toUserEntity(User user);

    List<User> toUsers(List<UserEntity> userEntities);

}
