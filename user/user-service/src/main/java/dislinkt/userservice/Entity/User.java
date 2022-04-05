package dislinkt.userservice.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.RequestEntity;

import lombok.Data;

@Data
@Document("users")
public class User {
    
    @Id 
    private Long id;

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
    private String userSkills;
    private String interests;
    private String privateAccount;

    // constructor used for registration
    public User(RequestEntity<User> user) {
        this.username = user.getBody().getUsername();
        this.passwordInput = user.getBody().getPasswordInput();
        this.email = user.getBody().getEmail();
        this.number = user.getBody().getNumber();
        this.firstName = user.getBody().getFirstName();
        this.lastName = user.getBody().getLastName();
        this.gender = user.getBody().getGender();
        this.dateOfBirth = user.getBody().getDateOfBirth();
        this.biography = user.getBody().getBiography();
        this.workExperience = user.getBody().getWorkExperience();
        this.studies = user.getBody().getStudies();
        this.userSkills = user.getBody().getUserSkills();
        this.interests = user.getBody().getInterests();
        this.privateAccount = user.getBody().getPrivateAccount();
    }

    // constructor used for login
    public User(RequestEntity<User> user, boolean login) {
        this.username = user.getBody().getUsername();
        this.passwordInput = user.getBody().getPasswordInput();
    }
}
