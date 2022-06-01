package dislinkt.userservice.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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

    public String getUserIdByApiToken(String apiToken) {
        User user = userRepository.findByApiToken(apiToken);
        if (user != null) {
            return user.getId();
        }
        return null;
    }

    public String generateAPIToken(String userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 48;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphaNumeric.length());
            sb.append(alphaNumeric.charAt(index));
        }
        String token = sb.toString();
        user.setApiToken(token);
        if (userRepository.save(user) != null) {
            return token;
        } else {
            return null;
        }
    }

    public void generateUsers() {
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUsername("user" + i);
            user.setPasswordInput("password" + i);
            user.setEmail("email" + i + "@email.com");
            user.setNumber("number" + i);
            user.setFirstName("firstName" + i);
            user.setLastName("lastName" + i);
            if (i % 2 == 0) {
                user.setGender("Male");
            } else {
                user.setGender("Female");
            }
            LocalDateTime dob = LocalDateTime.now();
            dob.minusDays(10 + i);
            user.setDateOfBirth(dob);
            user.setBiography("user: " + i + " biography");

            user.setWorkExperience(new ArrayList<>());
            user.setStudies(new ArrayList<>());
            user.setSkills(new ArrayList<>());
            user.setInterests(new ArrayList<>());

            user.getWorkExperience().add("user: " + i + " work experience");
            user.getStudies().add("user: " + i + " studies");
            user.getSkills().add("user: " + i + " skills");
            user.getInterests().add("user: " + i + " interests");

            if (i % 3 == 0) {
                user.setPrivateAccount(true);
            } else {
                user.setPrivateAccount(false);
            }
            user.setConnectionUserIds(new ArrayList<String>());
            user.setConnectionRequestUserIds(new ArrayList<String>());
            user.setPendingRequestUserIds(new ArrayList<String>());
            user.setBlockedUserIds(new ArrayList<String>());

            user.setRole("Client");

            register(userMapper.entityToDto(user));
        }

    }

    public UserDTO findById(String userId) {
        User user = userRepository.findById(userId).get();
        if (user != null) {
            System.out.println("user found");
            return userMapper.entityToDto(user);
        }
        System.out.println("user not found");
        return null;
    }

    public ArrayList<String> getAllUserIds() {
        ArrayList<String> userIds = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userIds.add(user.getId());
        }
        return userIds;
    }

    public ArrayList<String> getConnectionUserIds(String userId) {
        User user = userRepository.findById(userId).get();
        if (user != null) {
            System.out.println("user found");
            if (user.getConnectionUserIds() != null) {
                for (String id : user.getConnectionUserIds())
                    System.out.println(id);
                return user.getConnectionUserIds();
            }
        }
        return null;
    }

    public Boolean blockUser(String userId, String toBlockUserId) {
        User user = userRepository.findById(userId).get();
        User toBlockUser = userRepository.findById(toBlockUserId).get();
        if (user.getBlockedUserIds() == null) {
            user.setBlockedUserIds(new ArrayList<>());
        } else if (user.getBlockedUserIds().contains(toBlockUserId)) {
            System.out.println("User is already blocked");
            return false;
        }
        if (user.getConnectionRequestUserIds() != null && user.getConnectionUserIds().contains(toBlockUserId)) {
            user.getConnectionUserIds().remove(toBlockUserId);
        }
        if (user.getConnectionRequestUserIds() != null && user.getConnectionRequestUserIds().contains(toBlockUserId)) {
            user.getConnectionRequestUserIds().remove(toBlockUserId);
            toBlockUser.getPendingRequestUserIds().remove(userId);
        }
        if (toBlockUser.getConnectionUserIds() != null && toBlockUser.getConnectionUserIds().contains(userId)) {
            toBlockUser.getConnectionUserIds().remove(userId);
        }
        if (toBlockUser.getConnectionRequestUserIds() != null
                && toBlockUser.getConnectionRequestUserIds().contains(userId)) {
            toBlockUser.getConnectionRequestUserIds().remove(userId);
            user.getPendingRequestUserIds().remove(toBlockUserId);
        }
        user.getBlockedUserIds().add(toBlockUserId);
        if (userRepository.save(user) != null && userRepository.save(toBlockUser) != null) {
            System.out.println("User blocked successfully");
            return true;
        }
        return false;
    }

    public Boolean updatePrivacy(String userId) {
        User user = userRepository.findById(userId).get();
        System.out.println("User: " + user.getId());
        user.setPrivateAccount(!user.getPrivateAccount());
        if (userRepository.save(user) != null) {
            System.out.println("User privacy updated.");
            return true;
        }
        System.out.println("User privacy update failed.");
        return false;
    }

    public UserDTO viewUser(String userId, String toViewUserId) {
        User toViewUser = userRepository.findById(toViewUserId).get();
        if (userId.equals("")) {
            // unregistered user so he can only view public profiles
            if (toViewUser.getPrivateAccount()) {
                System.out.println("User is private");
                return null;
            }
        } else {
            User user = userRepository.findById(userId).get();
            if (toViewUser.getPrivateAccount()) {
                if (user.getConnectionRequestUserIds().contains(toViewUserId)) {
                    return userMapper.entityToDto(toViewUser);
                } else {
                    System.out.println("User is private and user is not connected");
                    return null;
                }
            }
        }
        return userMapper.entityToDto(toViewUser);
    }

    public Boolean approveFollow(String userId, String followerUserId) {
        User user = userRepository.findById(userId).get();
        User followerUser = userRepository.findById(followerUserId).get();
        if (user.getConnectionRequestUserIds() != null && user.getConnectionRequestUserIds().contains(followerUserId)) {
            user.getConnectionRequestUserIds().remove(followerUserId);
            followerUser.getPendingRequestUserIds().remove(userId);
            if (followerUser.getConnectionUserIds() == null) {
                followerUser.setConnectionUserIds(new ArrayList<>());
            }
            followerUser.getConnectionUserIds().add(userId);
            if (userRepository.save(user) != null && userRepository.save(followerUser) != null) {
                System.out.println("User '" + userId + "' approved follow request from '" + followerUserId + "'.");
                return true;
            }
            System.out.println("User '" + userId + "' failed to approve follow request from '" + followerUserId + "'.");
            return false;
        }
        System.out.println("No such request exists from '" + followerUserId + "' to '" + userId + "'.");
        return false;
    }

    public Boolean followUser(String userId, String toFollowUserId) {
        User user = userRepository.findById(userId).get();
        User toFollowUser = userRepository.findById(toFollowUserId).get();
        if (user.getConnectionUserIds() != null && user.getConnectionUserIds().contains(toFollowUserId)) {
            System.out.println("User '" + userId + "' already follows user '" + toFollowUserId + "'.");
            return false;
        }
        if (toFollowUser.getBlockedUserIds() != null && toFollowUser.getBlockedUserIds().contains(userId)) {
            System.out.println("User '" + userId + "' cannot follow user '" + toFollowUserId + "' because user '"
                    + toFollowUserId + "' has blocked user '" + userId + "'.");
            return false;
        }
        if (toFollowUser.getPrivateAccount()) {
            System.out.println("User '" + toFollowUserId + "' is private.");
            if (toFollowUser.getConnectionRequestUserIds() != null
                    && toFollowUser.getConnectionRequestUserIds().contains(userId)) {
                System.out.println("User '" + userId + "' already requested to follow user '" + toFollowUserId + "'.");
                return false;
            }
            if (toFollowUser.getConnectionRequestUserIds() == null) {
                toFollowUser.setConnectionRequestUserIds(new ArrayList<>());
            }
            if (user.getPendingRequestUserIds() == null) {
                user.setPendingRequestUserIds(new ArrayList<>());
            }
            toFollowUser.getConnectionRequestUserIds().add(userId);
            user.getPendingRequestUserIds().add(toFollowUserId);
            if (userRepository.save(toFollowUser) != null && userRepository.save(user) != null) {
                System.out.println(
                        "User '" + userId + "' successfully requested to follow user '" + toFollowUserId + "'.");
                return true;
            }
            System.out.println("User '" + userId + "' could not request to follow user '" + toFollowUserId + "'.");
            return false;
        }
        if (user.getConnectionUserIds() == null) {
            user.setConnectionUserIds(new ArrayList<>());
        }
        user.getConnectionUserIds().add(toFollowUserId);
        if (userRepository.save(user) != null) {
            System.out.println("User '" + userId + "' successfully followed user '" + toFollowUserId + "'.");
            return true;
        }
        System.out.println("User '" + userId + "' could not follow user '" + toFollowUserId + "'.");
        return false;
    }

    public ArrayList<UserDTO> find(String firstName, String lastName) {
        if (lastName == null) {
            return findByOneParam(firstName);
        }
        return findByName(firstName, lastName);
    }

    public ArrayList<UserDTO> findByOneParam(String searchParam) {
        ArrayList<UserDTO> usersFirstName = findByFirstName(searchParam);
        ArrayList<UserDTO> usersLastName = findByLastName(searchParam);
        ArrayList<UserDTO> usersUsername = findByUsername(searchParam);
        ArrayList<UserDTO> usersDTO = new ArrayList<>();

        if (usersFirstName == null && usersLastName == null && usersUsername == null) {
            return null;
        }
        if (usersFirstName == null && usersLastName == null) {
            usersDTO.addAll(usersUsername);
            return usersDTO;
        }
        if (usersLastName == null && usersUsername == null) {
            return usersFirstName;
        }
        if (usersFirstName == null && usersUsername == null) {
            return usersLastName;
        }
        if (usersUsername == null) {
            return mergeArrayLists(usersFirstName, usersLastName);
        }
        if (usersLastName == null) {
            return mergeArrayLists(usersFirstName, usersUsername);
        }
        if (usersFirstName == null) {
            return mergeArrayLists(usersLastName, usersUsername);
        }
        return mergeArrayLists(mergeArrayLists(usersFirstName, usersLastName), usersUsername);
    }

    public ArrayList<UserDTO> findByName(String firstName, String lastName) {
        ArrayList<UserDTO> usersDTO1 = findByFirstName(firstName);
        ArrayList<UserDTO> usersDTO2 = findByLastName(lastName);
        ArrayList<UserDTO> usersDTO3 = findByFirstName(lastName);
        ArrayList<UserDTO> usersDTO4 = findByLastName(firstName);

        ArrayList<UserDTO> usersDTO5 = mergePublic(usersDTO1, usersDTO2);
        ArrayList<UserDTO> usersDTO6 = mergePublic(usersDTO3, usersDTO4);
        ArrayList<UserDTO> usersDTO = mergeArrayLists(usersDTO5, usersDTO6);

        for (UserDTO user : usersDTO) {
            user.setPasswordSalt(null);
            user.setPasswordHash(null);
        }
        if (usersDTO.isEmpty()) {
            System.out.println("No users found");
            return null;
        }
        return usersDTO;
    }

    public ArrayList<UserDTO> findByFirstName(String firstName) {
        ArrayList<User> users = (ArrayList<User>) userRepository.findByFirstName(firstName);
        ArrayList<UserDTO> userDTOs = mapToDTO(users);
        if (userDTOs.isEmpty()) {
            System.out.println("No users found with the first name : '" + firstName + "'.");
            return null;
        }
        return userDTOs;
    }

    public ArrayList<UserDTO> findByLastName(String lastName) {
        ArrayList<User> users = userRepository.findByLastName(lastName);
        ArrayList<UserDTO> userDTOs = mapToDTO(users);
        if (userDTOs.isEmpty()) {
            System.out.println("No users found with the last name : '" + lastName + "'.");
            return null;
        }
        return userDTOs;
    }

    public ArrayList<UserDTO> findByUsername(String username) {
        ArrayList<User> users = userRepository.findByUsername(username);
        ArrayList<UserDTO> userDTOs = new ArrayList<>();
        if (users == null) {
            System.out.println("No user found with the username : '" + username + "'.");
            return null;
        }
        for (User user : users) {
            if (!user.getPrivateAccount()) {
                user.setPasswordSalt(null);
                user.setPasswordHash(null);
                System.out.println("User found with the username : '" + username + "'.");
                userDTOs.add(userMapper.entityToDto(user));
            }
        }
        if (!userDTOs.isEmpty()) {
            return userDTOs;
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
        ArrayList<User> users = userRepository.findByUsername(oldUsername);
        User allegedUser = null;
        for (User user : users) {
            if (user.getUsername().equals(oldUsername)) {
                allegedUser = user;
            }
        }
        if (allegedUser == null) {
            System.out.println("No user found with the username : '" + oldUsername + "'.");
            return null;
        }
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
        ArrayList<User> users = userRepository.findByUsername(userDTO.getUsername());
        User user = null;
        for (User user2 : users) {
            if (user2.getUsername().equals(userDTO.getUsername())) {
                user = user2;
            }
        }
        if (user == null) {
            System.out.println("User not found.");
            return null;
        }
        if (userDTO.getFirstName() != null && !user.getFirstName().equals(userDTO.getFirstName())) {
            user.setFirstName(userDTO.getFirstName());
            System.out.println("First name updated.");
        }
        if (userDTO.getLastName() != null && !user.getLastName().equals(userDTO.getLastName())) {
            user.setLastName(userDTO.getLastName());
            System.out.println("Last name updated.");
        }
        if (userDTO.getEmail() != null && !user.getEmail().equals(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
            System.out.println("Email updated.");
        }
        if (userDTO.getNumber() != null && !user.getNumber().equals(userDTO.getNumber())) {
            user.setNumber(userDTO.getNumber());
            System.out.println("Number updated.");
        }
        if (userDTO.getGender() != null && !user.getGender().equals(userDTO.getGender())) {
            user.setGender(userDTO.getGender());
            System.out.println("Gender updated.");
        }
        if (userDTO.getBiography() != null && !user.getBiography().equals(userDTO.getBiography())) {
            user.setBiography(userDTO.getBiography());
            System.out.println("Biography updated.");
        }
        if (userDTO.getWorkExperience() != null && !user.getWorkExperience().equals(userDTO.getWorkExperience())) {
            user.setWorkExperience(userDTO.getWorkExperience());
            System.out.println("Work experience updated.");
        }
        if (userDTO.getStudies() != null && !user.getStudies().equals(userDTO.getStudies())) {
            user.setStudies(userDTO.getStudies());
            System.out.println("Studies updated.");
        }
        if (userDTO.getSkills() != null && !user.getSkills().equals(userDTO.getSkills())) {
            user.setSkills(userDTO.getSkills());
            System.out.println("Skills updated.");
        }
        if (userDTO.getInterests() != null && !user.getInterests().equals(userDTO.getInterests())) {
            user.setInterests(userDTO.getInterests());
            System.out.println("Interests updated.");
        }

        if (userRepository.save(user) != null) {
            System.out.println("User '" + user.getUsername() + "' updated.");
            user.setPasswordInput("");
            user.setPasswordSalt(null);
            user.setPasswordHash(null);
            return user;
        }
        System.out.println("User '" + user.getUsername() + "' not updated.");
        return null;
    }

    public Boolean checkUsername(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    public User login(UserDTO incomingUser) {
        User user = userMapper.dtoToEntity(incomingUser);
        ArrayList<User> users = userRepository.findByUsername(user.getUsername());
        User allegedUser = null;
        for (User user2 : users) {
            if (user2.getUsername().equals(user.getUsername())) {
                allegedUser = user2;
                break;
            }
        }
        if (allegedUser == null) {
            System.out.println("User not found.");
            System.out.println("Attempted username : '" + incomingUser.getUsername() + "'.");
            return null;
        }

        user.setPasswordSalt(allegedUser.getPasswordSalt());
        SecretKeyFactory skf;

        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(user.getPasswordInput().toCharArray(), user.getPasswordSalt(), 65536, 256);
            byte[] passwordHash;
            try {
                passwordHash = skf.generateSecret(spec).getEncoded();
                if (Arrays.equals(passwordHash, allegedUser.getPasswordHash())) {
                    System.out.println("User '" + user.getUsername() + "' has successfully logged in.");
                    allegedUser.setPasswordSalt(null);
                    allegedUser.setPasswordHash(null);
                    return allegedUser;
                } else {
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
        // TODO: Temporary hack to bypass wrong mapping from String to LocalDateTime by
        // MapStruct
        // That's because of the wrong input string, it doesn't contain HH:mm that
        // LocalDateTime needs
        // So either convert to LocalDate or append String

        // TODO: imma add another temporary hack
        LocalDateTime x = null;
        if (incomingUser.getDateOfBirth() != null) {
            if (incomingUser.getDateOfBirth().length() > 11) {
                x = LocalDateTime.parse(incomingUser.getDateOfBirth());
            } else {
                LocalDate n = LocalDate.parse(incomingUser.getDateOfBirth());
                x = LocalDateTime.of(n, LocalTime.now());
            }
        }
        incomingUser.setDateOfBirth(null); // To bypass error

        User user = userMapper.dtoToEntity(incomingUser);
        user.setDateOfBirth(x);

        user.setRole("Client");
        user.setConnectionUserIds(null);
        user.setConnectionRequestUserIds(null);
        user.setPendingRequestUserIds(null);
        user.setBlockedUserIds(null);

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
                user.setPasswordInput(null);
                ArrayList<User> users = userRepository.findByUsername(user.getUsername());
                for (User user1 : users) {
                    if (user1.getUsername().equalsIgnoreCase(user.getUsername())) {
                        System.out.println("User '" + user.getUsername() + "' already exists.");
                        return null;
                    }
                }
                user = userRepository.save(user);
                if (user != null) {
                    System.out.println("User was successfully created.");
                    user.setPasswordSalt(null);
                    user.setPasswordHash(null);
                    return user;
                } else {
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

    public ArrayList<UserDTO> mapToDTO(ArrayList<User> users) {
        ArrayList<UserDTO> usersDTO = new ArrayList<UserDTO>();
        for (User user : users) {
            if (!user.getPrivateAccount()) {
                user.setPasswordSalt(null);
                user.setPasswordHash(null);
                usersDTO.add(userMapper.entityToDto(user));
            }
        }
        return usersDTO;
    }

    public ArrayList<UserDTO> mergeArrayLists(ArrayList<UserDTO> usersDTO1, ArrayList<UserDTO> usersDTO2) {
        ArrayList<UserDTO> usersDTO = new ArrayList<>();
        usersDTO.addAll(usersDTO1);
        boolean flag = false;
        for (UserDTO userDTO : usersDTO2) {
            for (UserDTO userDTO1 : usersDTO) {
                if (userDTO.getId().equals(userDTO1.getId())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                usersDTO.add(userDTO);
            }
            flag = false;
        }
        return usersDTO;
    }

    public ArrayList<UserDTO> addOne(ArrayList<UserDTO> usersDTO, UserDTO userDTO) {
        boolean flag = false;
        for (UserDTO userDTO1 : usersDTO) {
            if (userDTO1.getId().equals(userDTO.getId())) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

    public ArrayList<UserDTO> mergePublic(ArrayList<UserDTO> usersDTO1, ArrayList<UserDTO> usersDTO2) {
        ArrayList<UserDTO> usersDTO = new ArrayList<>();
        for (UserDTO user : usersDTO1) {
            for (UserDTO user1 : usersDTO2) {
                if (user.getId().equals(user1.getId()) && !user.getPrivateAccount()) {
                    System.out.println("User found: " + user1.getFirstName() + " " + user1.getLastName());
                    usersDTO.add(user);
                }
            }
        }
        return usersDTO;
    }
}
