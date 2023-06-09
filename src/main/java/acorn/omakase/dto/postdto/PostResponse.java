package acorn.omakase.dto.postdto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class PostResponse {

    private String title;
    private String content;
    private String createdAt;
    private String category;
    private String nickname;
}
