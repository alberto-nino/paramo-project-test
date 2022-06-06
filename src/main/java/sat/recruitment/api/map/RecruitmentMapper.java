package sat.recruitment.api.map;

import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;
import sat.recruitment.api.dto.UserDTO;
import sat.recruitment.api.entity.User;

import java.util.List;


@Mapper(componentModel = "spring")
public interface RecruitmentMapper {

    RecruitmentMapper INSTANCE = Mappers.getMapper( RecruitmentMapper.class );

    @ValueMapping(target = "NORMAL", source = "Normal")
    UserDTO userToUserDTO(User user);

    List<UserDTO> usersToUsersDTO(List<User> users);

    @ValueMapping(target = "Normal", source = "NORMAL")
    User userDTOTOUser(UserDTO userDTO);
}
