package acorn.omakase.domain;

import acorn.omakase.dto.userdto.LoginRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Login {
    private String loginId;
    private String password;

    @Builder
    public Login(String loginId, String password) {

        this.loginId = loginId;
        this.password = password;
    }

    public static Login of(LoginRequest loginRequest) {
        return Login.builder()
                .loginId(loginRequest.getLoginId())
                .password(loginRequest.getPassword()).build();
    }
}
