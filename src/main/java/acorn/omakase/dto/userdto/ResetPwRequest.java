package acorn.omakase.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResetPwRequest {
    private String loginId;
    private String pw1;
    private String pw2;
}
