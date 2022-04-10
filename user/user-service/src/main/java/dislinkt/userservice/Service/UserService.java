package dislinkt.userservice.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Service;

import ch.qos.logback.core.net.SyslogOutputStream;
import dislinkt.userclient.UserDTO;
import dislinkt.userservice.Entity.User;
import dislinkt.userservice.Mapper.UserMapper;
import dislinkt.userservice.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public User updateUsername(User user, String username) {
        if (checkUsername(username)) {
            user.setUsername(username);
            if (userRepository.save(user) != null) {
                System.out.println("Username updated.");
                return user;
            }
            System.out.println("Username not updated.");
            return null;
        }
        System.out.println("Username already exists.");
        return null;
    }

    public User update(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());

        if (user == null) {
            return null;
        }

        if (!user.getFirstName().equals(userDTO.getFirstName())) {
            user.setFirstName(userDTO.getFirstName());
            System.out.println("First name updated.");
        }
        if (!user.getLastName().equals(userDTO.getLastName())) {
            user.setLastName(userDTO.getLastName());
            System.out.println("Last name updated.");
        }
        if (!user.getEmail().equals(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
            System.out.println("Email updated.");
        }
        if (!user.getNumber().equals(userDTO.getNumber())) {
            user.setNumber(userDTO.getNumber());
            System.out.println("Number updated.");
        }
        if (!user.getGender().equals(userDTO.getGender())) {
            user.setGender(userDTO.getGender());
            System.out.println("Gender updated.");
        }
        if (!user.getDateOfBirth().equals(userDTO.getDateOfBirth())) {
            user.setDateOfBirth(userDTO.getDateOfBirth());
            System.out.println("Date of birth updated.");
        }
        if (!user.getBiography().equals(userDTO.getBiography())) {
            user.setBiography(userDTO.getBiography());
            System.out.println("Biography updated.");
        }
        if (!user.getWorkExperience().equals(userDTO.getWorkExperience())) {
            user.setWorkExperience(userDTO.getWorkExperience());
            System.out.println("Work experience updated.");
        }
        if (!user.getStudies().equals(userDTO.getStudies())) {
            user.setStudies(userDTO.getStudies());
            System.out.println("Studies updated.");
        }
        if (!user.getSkills().equals(userDTO.getSkills())) {
            user.setSkills(userDTO.getSkills());
            System.out.println("Skills updated.");
        }
        if (!user.getInterests().equals(userDTO.getInterests())) {
            user.setInterests(userDTO.getInterests());
            System.out.println("Interests updated.");
        }

        if (userRepository.save(user) != null) {
            System.out.println("User '" + user.getUsername() + "' updated.");
            return user;
        }

        System.out.println("User '" + user.getUsername() + "' not updated.");
        return null;
    }

    public Boolean checkUsername(String username) {
        return userRepository.findByUsername(username) == null;
    }

    public User login(UserDTO incomingUser) {
        User user = userMapper.dtoToEntity(incomingUser);
        User allegedUser = userRepository.findByUsername(user.getUsername());

        if (allegedUser == null) {
            System.out.println("User not found.");
            System.out.println("Attempted username : '" + incomingUser.getUsername() + "'.");
        }

        user.setPasswordSalt(allegedUser.getPasswordSalt());
        SecretKeyFactory skf;
        
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(user.getPasswordInput().toCharArray(), user.getPasswordSalt(), 65536, 256);
            byte[] passwordHash;
            try {
                passwordHash = skf.generateSecret(spec).getEncoded();
                if (Arrays.equals(passwordHash,allegedUser.getPasswordHash())) {
                    System.out.println("User '" + user.getUsername() + "' has successfully logged in.");
                    return allegedUser;
                }
                else {
                    System.out.println("Wrong password.");
                    System.out.println("Attempted username : '" + user.getUsername() + "'.");
                    return null;
                }
            } catch (InvalidKeySpecException e) {
                System.out.println("Invalid key spec.");
                e.printStackTrace();
                return null;
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm.");
            e.printStackTrace();
            return null;
        }
    }   

    public User register(UserDTO incomingUser) {
        User user = userMapper.dtoToEntity(incomingUser);
        
        // password handling
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        user.setPasswordSalt(salt);
        SecretKeyFactory skf;

        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(user.getPasswordInput().toCharArray(), user.getPasswordSalt(), 65536, 256);
            byte[] passwordHash;
            try {
                passwordHash = skf.generateSecret(spec).getEncoded();
                user.setPasswordHash(passwordHash);
                user.setPasswordInput("");
                if (userRepository.findByUsername(user.getUsername()) != null) {
                    System.out.println("User '" + user.getUsername() + "' already exists.");
                    return null;
                }
                if (userRepository.save(user) != null) {
                    System.out.println("User was successfully created.");
                    return user;
                }
                else {
                    System.out.println("User was not saved.");
                    return null;
                }
            } catch (InvalidKeySpecException e) {
                System.out.println("Invalid key spec.");
                e.printStackTrace();
                return null;
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm.");
            e.printStackTrace();
            return null;
        }
    }

    


}
