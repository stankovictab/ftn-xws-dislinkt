package dislinkt.agentclient;

import java.util.ArrayList;

import lombok.Data;

@Data
public class OfferDTO {

    private String id;

    private String firmId;

    private String jobTittle;

    private String jobDescription;

    private String jobLocation;

    private String jobSeniority;
    
    private String jobField;

    private ArrayList<String> jobTechnologies;

    private ArrayList<String> jobResponsibilities;

    private ArrayList<String> jobRequirements;

    private ArrayList<String> jobBonuses;

    private Boolean dislinktShare;
    
}
