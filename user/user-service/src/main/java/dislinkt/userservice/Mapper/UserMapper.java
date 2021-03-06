package dislinkt.userservice.Mapper;

import java.util.ArrayList;

import org.mapstruct.Mapper;

import dislinkt.userclient.UserDTO;
import dislinkt.userservice.Entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO entityToDto(User user);

    User dtoToEntity(UserDTO userDTO);

    ArrayList<UserDTO> toUserDTOList(ArrayList<UserDTO> findByFirstName);
    
}
