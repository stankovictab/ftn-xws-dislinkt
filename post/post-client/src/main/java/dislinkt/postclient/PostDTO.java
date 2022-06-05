package dislinkt.postclient;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lombok.Data;

@Data
public class PostDTO {
    private String id;
    private String title;
    private String description;
    private String embedLink;
	private String imageTitle;
	private String imageId;
    private String userId;
    private String authorName;
	private String apiToken;

	private String offerId;
	private String firmId;
	private String jobTitle;
	private String jobDescription;
	private String jobLocation;
	private String jobSeniority;
	private String jobField;
	private ArrayList<String> jobTechnologies;
	private ArrayList<String> jobResponsibilities;
	private ArrayList<String> jobRequirements;
	private ArrayList<String> jobBonuses;

    private int likes;
    private int dislikes;
    private LocalDateTime creationDate;

    private ArrayList<String> likedUserIds;
    private ArrayList<String> dislikedUserIds;

	private ArrayList<CommentDTO> comments;
    
}
