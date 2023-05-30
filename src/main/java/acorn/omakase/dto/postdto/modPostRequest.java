package acorn.omakase.dto.postdto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class modPostRequest {
    private long postId;
    private String title;
    private String content;
    private String category;
}
