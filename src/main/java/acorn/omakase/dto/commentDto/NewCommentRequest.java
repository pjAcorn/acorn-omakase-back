package acorn.omakase.dto.commentDto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class NewCommentRequest {
    private String commentContent;
    private Long postId;
}
