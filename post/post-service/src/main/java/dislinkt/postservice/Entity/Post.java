package dislinkt.postservice.Entity;

import java.time.LocalTime;

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
	private String image;
	private String userId;

	private int likes;
	private int dislikes;

	private LocalTime creationDate;

}
