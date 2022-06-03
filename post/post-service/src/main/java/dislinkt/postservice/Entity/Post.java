package dislinkt.postservice.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("posts")
public class Post {

	@Id
	private String id;

	private String title;
	private String description;
	private String embedLink;
	private String imageTitle;
	private String imageId;
	private String userId;
	private String authorName;

	private String apiToken;

	// offer part
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

	private ArrayList<String> likedUserIds;
	private ArrayList<String> dislikedUserIds;

	private LocalDateTime creationDate;

}
