package dislinkt.user.Entity;

import java.util.ArrayList;

import lombok.Data;

@Data
public class UserDTO {

    private String id;

    private String username;

    private String passwordInput;
    private byte[] passwordSalt;
    private byte[] passwordHash;

    private String email;
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

    private String role;
}