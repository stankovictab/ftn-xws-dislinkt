package dislinkt.post.Entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostDTO {
    private String id;
    private String title;
    private String description;
    private String image;
    private String userId;
    private int likes;
    private int dislikes;
    private LocalDateTime creationDate;
    
}
