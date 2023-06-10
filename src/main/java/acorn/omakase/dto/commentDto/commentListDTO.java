package acorn.omakase.dto.commentDto;

import acorn.omakase.dto.postdto.SearchDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class commentListDTO extends SearchDto {
    private String content;
    private String nickname;
    private Date createdAt;
    private Date updatedAt;
}
