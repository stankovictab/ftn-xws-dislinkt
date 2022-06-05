package dislinkt.agentclient;

import java.util.ArrayList;

import lombok.Data;

@Data
public class FirmDTO {

    private String id;

    private String ownerId;

    private String email;
    private String number;

    private String name;
    private String description;
    private String culture;

    private Boolean approved;

    // has a list of ids that are the ids of specific job offers
    // hence a job offer class is required
    private ArrayList<String> offerIds;

    // maybe has a list of comments of users about the firm

    private String apiToken;


    private ArrayList<String> firmCommentIds;
}