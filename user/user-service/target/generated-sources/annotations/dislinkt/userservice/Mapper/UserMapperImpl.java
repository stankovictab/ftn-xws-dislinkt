package dislinkt.userservice.Mapper;

import dislinkt.userclient.UserDTO;
import dislinkt.userservice.Entity.User;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-11T21:08:36+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Private Build)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO entityToDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPasswordInput( user.getPasswordInput() );
        byte[] passwordSalt = user.getPasswordSalt();
        if ( passwordSalt != null ) {
            userDTO.setPasswordSalt( Arrays.copyOf( passwordSalt, passwordSalt.length ) );
        }
        byte[] passwordHash = user.getPasswordHash();
        if ( passwordHash != null ) {
            userDTO.setPasswordHash( Arrays.copyOf( passwordHash, passwordHash.length ) );
        }
        userDTO.setEmail( user.getEmail() );
        userDTO.setNumber( user.getNumber() );
        userDTO.setFirstName( user.getFirstName() );
        userDTO.setLastName( user.getLastName() );
        userDTO.setGender( user.getGender() );
        userDTO.setDateOfBirth( user.getDateOfBirth() );
        userDTO.setBiography( user.getBiography() );
        userDTO.setWorkExperience( user.getWorkExperience() );
        userDTO.setStudies( user.getStudies() );
        userDTO.setSkills( user.getSkills() );
        userDTO.setInterests( user.getInterests() );
        userDTO.setPrivateAccount( user.getPrivateAccount() );

        return userDTO;
    }

    @Override
    public User dtoToEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setPasswordInput( userDTO.getPasswordInput() );
        byte[] passwordSalt = userDTO.getPasswordSalt();
        if ( passwordSalt != null ) {
            user.setPasswordSalt( Arrays.copyOf( passwordSalt, passwordSalt.length ) );
        }
        byte[] passwordHash = userDTO.getPasswordHash();
        if ( passwordHash != null ) {
            user.setPasswordHash( Arrays.copyOf( passwordHash, passwordHash.length ) );
        }
        user.setEmail( userDTO.getEmail() );
        user.setNumber( userDTO.getNumber() );
        user.setFirstName( userDTO.getFirstName() );
        user.setLastName( userDTO.getLastName() );
        user.setGender( userDTO.getGender() );
        user.setDateOfBirth( userDTO.getDateOfBirth() );
        user.setBiography( userDTO.getBiography() );
        user.setWorkExperience( userDTO.getWorkExperience() );
        user.setStudies( userDTO.getStudies() );
        user.setSkills( userDTO.getSkills() );
        user.setInterests( userDTO.getInterests() );
        user.setPrivateAccount( userDTO.getPrivateAccount() );

        return user;
    }

    @Override
    public ArrayList<UserDTO> toUserDTOList(ArrayList<UserDTO> findByFirstName) {
        if ( findByFirstName == null ) {
            return null;
        }

        ArrayList<UserDTO> arrayList = new ArrayList<UserDTO>();
        for ( UserDTO userDTO : findByFirstName ) {
            arrayList.add( userDTO );
        }

        return arrayList;
    }
}
