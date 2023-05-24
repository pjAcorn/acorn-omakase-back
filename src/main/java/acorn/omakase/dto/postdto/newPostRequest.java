package acorn.omakase.dto.postdto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter @Getter
public class newPostRequest {
    private String title;
    private String content;
    private String nickname;
    private String category;
}
