package co.edu.ufps.innova.security.persistence.mapper;

import java.util.List;
import org.mapstruct.*;
import co.edu.ufps.innova.security.domain.dto.User;
import co.edu.ufps.innova.security.persistence.entity.UserEntity;

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
