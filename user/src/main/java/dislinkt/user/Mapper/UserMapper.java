package dislinkt.user.Mapper;

import java.util.ArrayList;

import org.mapstruct.Mapper;

import dislinkt.user.Entity.User;
import dislinkt.user.Entity.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO entityToDto(User user);

    User dtoToEntity(UserDTO userDTO);

    ArrayList<UserDTO> toUserDTOList(ArrayList<UserDTO> findByFirstName);
    
}
