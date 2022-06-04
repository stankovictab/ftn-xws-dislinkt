package dislinkt.agentclient;

import lombok.Data;

@Data
public class CommentDTO {
    private String id;

    private String firmId;

    private String userId;

    private String comment;

    private String date;

    private String rating;

    private String salary;
}
