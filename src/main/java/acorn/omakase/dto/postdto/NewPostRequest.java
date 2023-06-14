package acorn.omakase.dto.postdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewPostRequest {
    private String title;
    private String content;
    private String category;
}
