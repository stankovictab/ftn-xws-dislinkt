package dislinkt.post.Entity;

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
	private String image;
	private String userId;

	private int likes;
	private int dislikes;

	private ArrayList<String> likedUserIds;
	private ArrayList<String> dislikedUserIds;

	private LocalDateTime creationDate;

}