package dislinkt.userservice.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;

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

    public ArrayList<UserDTO> find(String firstName, String lastName) {
        ArrayList<UserDTO> usersDTO1 = new ArrayList<>();
        ArrayList<UserDTO> usersDTO2 = new ArrayList<>();
        ArrayList<UserDTO> usersDTO = new ArrayList<>();
        
        if (lastName == null) {
            usersDTO1 = findByFirstName(firstName);
            usersDTO2 = findByLastName(firstName);

            Boolean flag = false;
            if (usersDTO1 != null ) {
                for (UserDTO userDTO: usersDTO1) {
                    userDTO.setPasswordSalt(null);
                    userDTO.setPasswordHash(null);
                    usersDTO.add(userDTO);
                }
                for (UserDTO userDTO : usersDTO1) {
                    for (UserDTO userDTO2 : usersDTO) {
                        if (userDTO.getId().equals(userDTO2.getId())) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        userDTO.setPasswordSalt(null);
                        userDTO.setPasswordHash(null);
                        usersDTO.add(userDTO);
                        flag = false;
                    }
                }
            }
            if (usersDTO2 != null) {
                if (usersDTO.isEmpty()) {
                    for (UserDTO userDTO: usersDTO1) {
                        userDTO.setPasswordSalt(null);
                        userDTO.setPasswordHash(null);
                        usersDTO.add(userDTO);
                    }
                }
                else {
                    for (UserDTO userDTO: usersDTO2) {
                        for (UserDTO userDTO1: usersDTO) {
                            if (userDTO.getId().equals(userDTO1.getId())) {
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            userDTO.setPasswordSalt(null);
                            userDTO.setPasswordHash(null);
                            usersDTO.add(userDTO);
                        }
                        flag = false;
                    }
                }
            }
        }
        else {
            usersDTO = findByName(firstName, lastName);
        }        

        return usersDTO;
    }

    public ArrayList<UserDTO> findByName(String firstName, String lastName) {
        ArrayList<User> users = userRepository.findByFirstName(firstName);
        ArrayList<User> users2 = userRepository.findByLastName(lastName);
        ArrayList<User> users3 = userRepository.findByFirstName(lastName);
        ArrayList<User> users4 = userRepository.findByLastName(firstName);
        ArrayList<User> users5 = new ArrayList<User>();
        ArrayList<User> users6 = new ArrayList<User>();
        ArrayList<User> users7 = new ArrayList<User>();

        for (User user : users) {
            for (User user1 : users2) {
                if (user.getId().equals(user1.getId())) {
                    if (!user1.getPrivateAccount()) {
                        System.out.println("User found: " + user1.getFirstName() + " " + user1.getLastName());
                        users5.add(user);
                    }
                }
            }
        }

        for (User user : users3) {
            for (User user1 : users4) {
                if (user.getId().equals(user1.getId())) {
                    if (!user1.getPrivateAccount()) {
                        System.out.println("User found: " + user1.getFirstName() + " " + user1.getLastName());
                        users6.add(user);
                    }
                }
            }
        }

        Boolean flag = false;
        users7.addAll(users5);
        
        for (User user : users6) {
            for (User user1 : users7) {
                if (user.getId().equals(user1.getId())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                users7.add(user);
            }
            flag = false;
        }

        ArrayList<UserDTO> usersDTO = new ArrayList<UserDTO>();
        for (User user : users7) {
            user.setPasswordSalt(null);
            user.setPasswordHash(null);
            usersDTO.add(userMapper.entityToDto(user));
        }
        if (usersDTO.isEmpty()) {
            System.out.println("No users found");
            return null;
        } 

        return usersDTO;

    }

    public ArrayList<UserDTO> findByFirstName(String firstName) {
        ArrayList<User> users = (ArrayList<User>) userRepository.findByFirstName(firstName);
        ArrayList<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            if (!user.getPrivateAccount()) {
                user.setPasswordSalt(null);
                user.setPasswordHash(null);
                userDTOs.add(userMapper.entityToDto(user));
            }
        }
        if (userDTOs.isEmpty()) {
            System.out.println("No users found with the first name : '" + firstName + "'.");
            return null;
        }
        return userDTOs;
    }

    public ArrayList<UserDTO> findByLastName(String lastName) {
        ArrayList<User> users = userRepository.findByLastName(lastName);
        ArrayList<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            if (!user.getPrivateAccount()) {
                user.setPasswordSalt(null);
                user.setPasswordHash(null);
                userDTOs.add(userMapper.entityToDto(user));
            }
        }
        if (userDTOs.isEmpty()) {
            System.out.println("No users found with the last name : '" + lastName + "'.");
            return null;
        }
        return userDTOs;
    }

    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("No user found with the username : '" + username + "'.");
            return null;
        }
        if (!user.getPrivateAccount()) {
            user.setPasswordSalt(null);
            user.setPasswordHash(null);
            System.out.println("User found with the username : '" + username + "'.");
            return userMapper.entityToDto(user);
        }
        System.out.println("The user with the username : '" + username + "' is private.");
        return null;
    }

    public ArrayList<UserDTO> findAll() {
        ArrayList<UserDTO> userDTOs = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            user.setPasswordSalt(null);
            user.setPasswordHash(null);
            userDTOs.add(userMapper.entityToDto(user));
        }

        return userDTOs;
    }

    public User updateUsername(String oldUsername, String newUsername) {
        User allegedUser = userRepository.findByUsername(oldUsername);
        if (checkUsername(newUsername)) {
            allegedUser.setUsername(newUsername);
            if (userRepository.save(allegedUser) != null) {
                allegedUser.setPasswordSalt(null);
                allegedUser.setPasswordHash(null);
                System.out.println("Username updated.");
                return allegedUser;
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
            System.out.println("User not found.");
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
                    allegedUser.setPasswordSalt(null);
                    allegedUser.setPasswordHash(null);
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
        user.setRole("Client");
        
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
                    user.setPasswordSalt(null);
                    user.setPasswordHash(null);
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
