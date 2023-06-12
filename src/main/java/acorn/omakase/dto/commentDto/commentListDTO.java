package acorn.omakase.dto.commentDto;

import acorn.omakase.dto.postdto.SearchDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class commentListDTO extends SearchDto {
    private Long commentId;
    private String content;
    private String nickname;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long likeCount;
}
