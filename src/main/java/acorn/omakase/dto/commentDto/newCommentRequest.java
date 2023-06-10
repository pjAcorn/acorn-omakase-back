package acorn.omakase.dto.commentDto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class newCommentRequest {
    private String content;
    private Long userId;
    private Long postId;
}
