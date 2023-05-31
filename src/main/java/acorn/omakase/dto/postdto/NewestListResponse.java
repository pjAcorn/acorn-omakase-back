package acorn.omakase.dto.postdto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NewestListResponse {

    private List<NewestPostDto> newestPostList;
}
