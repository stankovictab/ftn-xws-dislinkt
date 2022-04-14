package dislinkt.userservice.Entity;

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
    private String dateOfBirth;
    private String biography;
    private String workExperience;
    private String studies;
    private String skills;
    private String interests;
    private Boolean privateAccount;

    // ids of users that the user is following
    private ArrayList<String> connectionUserIds;
    // ids of users that have requested to follow the user
    private ArrayList<String> connectionRequestUserIds;
    // ids of users that the user has requested to follow
    private ArrayList<String> pendingRequestUserIds;
    // ids of users that the user has blocked
    private ArrayList<String> blockedUserIds;

    private String role;

}
