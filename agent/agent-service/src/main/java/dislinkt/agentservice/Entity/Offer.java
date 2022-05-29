package dislinkt.agentservice.Entity;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("offers")
public class Offer {    

    @Id
    private String id;

    // for which firm the offer is made
    private String firmId;

    private String jobTittle;

    private String jobDescription;

    private String jobLocation;

    // junior, medior, senior
    private String jobSeniority;

    // frontend, backend, fullstack etc.
    private String jobField;

    private ArrayList<String> jobTechnologies;

    private ArrayList<String> jobResponsibilities;

    private ArrayList<String> jobRequirements;

    private ArrayList<String> jobBonuses;

    private Boolean dislinktShare;    
}
