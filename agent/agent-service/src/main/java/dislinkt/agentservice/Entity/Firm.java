package dislinkt.agentservice.Entity;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("firms")
public class Firm {
    
    @Id 
    private String id;

    private String ownerId;

    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String number;

    private String name;
    private String description;
    private String culture;

    private Boolean approved;

    // has a list of ids that are the ids of specific job offers
    private ArrayList<String> offerIds;

    // maybe has a list of comments of users about the firm

    private String apiToken;

    private ArrayList<String> firmCommentIds;

    
    

}