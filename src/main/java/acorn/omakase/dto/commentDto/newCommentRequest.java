package acorn.omakase.dto.commentDto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter @Getter
public class newCommentRequest {

    private String content;
    private Long like;
    private Date createdAt;
    private String nickname;
    private Long postId;
}
