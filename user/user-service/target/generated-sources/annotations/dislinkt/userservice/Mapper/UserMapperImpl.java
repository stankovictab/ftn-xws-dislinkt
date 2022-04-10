package dislinkt.userservice.Mapper;

import dislinkt.userclient.UserDTO;
import dislinkt.userservice.Entity.User;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-10T19:43:11+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO entityToDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setBiography( user.getBiography() );
        userDTO.setDateOfBirth( user.getDateOfBirth() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setFirstName( user.getFirstName() );
        userDTO.setGender( user.getGender() );
        userDTO.setId( user.getId() );
        userDTO.setInterests( user.getInterests() );
        userDTO.setLastName( user.getLastName() );
        userDTO.setNumber( user.getNumber() );
        byte[] passwordHash = user.getPasswordHash();
        if ( passwordHash != null ) {
            userDTO.setPasswordHash( Arrays.copyOf( passwordHash, passwordHash.length ) );
        }
        userDTO.setPasswordInput( user.getPasswordInput() );
        byte[] passwordSalt = user.getPasswordSalt();
        if ( passwordSalt != null ) {
            userDTO.setPasswordSalt( Arrays.copyOf( passwordSalt, passwordSalt.length ) );
        }
        userDTO.setPrivateAccount( user.getPrivateAccount() );
        userDTO.setSkills( user.getSkills() );
        userDTO.setStudies( user.getStudies() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setWorkExperience( user.getWorkExperience() );

        return userDTO;
    }

    @Override
    public User dtoToEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setBiography( userDTO.getBiography() );
        user.setDateOfBirth( userDTO.getDateOfBirth() );
        user.setEmail( userDTO.getEmail() );
        user.setFirstName( userDTO.getFirstName() );
        user.setGender( userDTO.getGender() );
        user.setId( userDTO.getId() );
        user.setInterests( userDTO.getInterests() );
        user.setLastName( userDTO.getLastName() );
        user.setNumber( userDTO.getNumber() );
        byte[] passwordHash = userDTO.getPasswordHash();
        if ( passwordHash != null ) {
            user.setPasswordHash( Arrays.copyOf( passwordHash, passwordHash.length ) );
        }
        user.setPasswordInput( userDTO.getPasswordInput() );
        byte[] passwordSalt = userDTO.getPasswordSalt();
        if ( passwordSalt != null ) {
            user.setPasswordSalt( Arrays.copyOf( passwordSalt, passwordSalt.length ) );
        }
        user.setPrivateAccount( userDTO.getPrivateAccount() );
        user.setSkills( userDTO.getSkills() );
        user.setStudies( userDTO.getStudies() );
        user.setUsername( userDTO.getUsername() );
        user.setWorkExperience( userDTO.getWorkExperience() );

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
