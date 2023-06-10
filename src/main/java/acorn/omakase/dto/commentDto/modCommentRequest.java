package acorn.omakase.dto.commentDto;

import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class modCommentRequest {
    private Long commentId;
    private String content;
}
