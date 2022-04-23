package dislinkt.postservice.Entity;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("images")
public class Image {

	@Id
	private String id;

	private String title;

	private Binary image;

	private String postId;

	public Image(String title) {
		this.title = title;
	}

}
