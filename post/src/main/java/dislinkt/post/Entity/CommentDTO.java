package dislinkt.post.Entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentDTO {
    private String id;
    private String userId;
    private String postId;
    private String comment;
    private LocalDateTime creationDate;
    
}
