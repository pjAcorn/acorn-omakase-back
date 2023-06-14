package acorn.omakase.dto.postdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;


@Getter
@AllArgsConstructor

public class PostResponse {

    private Long postId;
    private String title;
    private String content;
    private String createdAt;
    private String category;
    private String nickname;

}
