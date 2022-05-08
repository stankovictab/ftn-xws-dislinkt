package dislinkt.postclient;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostDTO {
    private String id;
    private String title;
    private String description;
	private String imageTitle;
	private String imageId;
    private String userId;
    private int likes;
    private int dislikes;
    private LocalDateTime creationDate;

    // TODO: setLikedUserIds
    // TODO: setDislikedUserIds
    
}
