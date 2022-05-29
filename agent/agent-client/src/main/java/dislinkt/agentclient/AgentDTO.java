package dislinkt.agentclient;

import lombok.Data;

@Data
public class AgentDTO {

    private String id;

    private String username;

    private String passwordInput;

    private String email;
    private String number;

    private String firstName;
    private String lastName;

    private String apiToken;

    private String role;
}