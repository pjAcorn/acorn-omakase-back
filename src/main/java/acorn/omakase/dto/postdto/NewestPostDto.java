package acorn.omakase.dto.postdto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class NewestPostDto extends SearchDto{

    private Long postId;
    private String title;
    private String nickname;
    private Date createdAt;

}
