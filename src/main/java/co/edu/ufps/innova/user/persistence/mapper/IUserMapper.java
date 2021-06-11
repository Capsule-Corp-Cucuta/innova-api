package co.edu.ufps.innova.user.persistence.mapper;

import org.mapstruct.*;
import co.edu.ufps.innova.user.domain.dto.User;
import co.edu.ufps.innova.user.persistence.entity.UserEntity;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    User toUser(UserEntity userEntity);

    @Mappings({
            @Mapping(target = "password", ignore = true),
    })
    @InheritInverseConfiguration
    UserEntity toUserEntity(User user);

    Set<User> toUsers(Set<UserEntity> userEntities);

}
