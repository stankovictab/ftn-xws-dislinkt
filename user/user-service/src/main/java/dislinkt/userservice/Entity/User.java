package dislinkt.userservice.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("users")
public class User {
    
    @Id 
    private String id;

    @Indexed(unique = true)
    private String username;

    private String passwordInput;
    private byte[] passwordSalt;
    private byte[] passwordHash;

    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String number;

    private String firstName;
    private String lastName;

    private String gender;
    private LocalDateTime dateOfBirth;
    private String biography;
    private ArrayList<String> workExperience;
    private ArrayList<String> studies;
    private ArrayList<String> skills;
    private ArrayList<String> interests;
    private Boolean privateAccount;

    // ids of users that the user is following
    private ArrayList<String> connectionUserIds;
    // ids of users that have requested to follow the user
    private ArrayList<String> connectionRequestUserIds;
    // ids of users that the user has requested to follow
    private ArrayList<String> pendingRequestUserIds;
    // ids of users that the user has blocked
    private ArrayList<String> blockedUserIds;

    @Indexed(unique = true)
    private String apiToken;
    

    private String role;

}
