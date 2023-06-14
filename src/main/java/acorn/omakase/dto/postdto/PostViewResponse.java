package acorn.omakase.dto.postdto;

import acorn.omakase.dto.commentDto.commentListDTO;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostViewResponse {
    private PostResponse post;
//    private PageInfo<commentListDTO> commentPage;
    private List comment;
}
