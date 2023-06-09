package acorn.omakase.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {
    private String loginId;
    private String password;
}
