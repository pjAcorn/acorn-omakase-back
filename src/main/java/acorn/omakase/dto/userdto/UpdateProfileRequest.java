package acorn.omakase.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {

    private Long userId;
    private String name;
    private String email;
    private String region;
    private String nickname;
}
