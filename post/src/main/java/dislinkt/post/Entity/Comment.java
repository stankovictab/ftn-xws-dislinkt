package dislinkt.post.Entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("comments")
public class Comment {

	@Id
	private String id;

	private String userId;
	private String postId;
	private String comment;

	private LocalDateTime creationDate;

}
