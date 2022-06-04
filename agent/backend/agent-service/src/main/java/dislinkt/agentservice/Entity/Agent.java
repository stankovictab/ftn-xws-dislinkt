package dislinkt.agentservice.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("agents")
public class Agent {

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

    private String apiToken;

    // 3 roles: Admin, Owner, User
    private String role;

}
