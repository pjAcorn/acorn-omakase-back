package acorn.omakase.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteIdRequest {
    private Long userId;
    private String password;
    private String password1;
    private String password2;


}
