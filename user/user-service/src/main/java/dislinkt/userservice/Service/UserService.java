package dislinkt.userservice.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Service;

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

    public User login(UserDTO user1) {
        User user = userMapper.dtoToEntity(user1);
        User allegedUser = userRepository.findByUsername(user.getUsername());

        if (allegedUser == null) {
            System.out.println("User not found.");
            System.out.println("Attempted username : '" + user1.getUsername() + "'.");
        }

        user.setPasswordSalt(allegedUser.getPasswordSalt());
        SecretKeyFactory skf;
        
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(user.getPasswordInput().toCharArray(), user.getPasswordSalt(), 65536, 256);
            byte[] passwordHash;
            try {
                passwordHash = skf.generateSecret(spec).getEncoded();
                if (allegedUser.getPasswordHash().equals(passwordHash)) {
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

    public User register(UserDTO user1) {
        User user = userMapper.dtoToEntity(user1);
        
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
