package dislinkt.userclient;

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

    private String role;
}