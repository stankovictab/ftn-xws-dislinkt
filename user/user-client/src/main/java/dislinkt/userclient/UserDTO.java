package dislinkt.userclient;

import lombok.Data;

/**
 * UserDTO
 */
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

    private String role;


}