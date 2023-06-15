package acorn.omakase.dto.userdto;

import lombok.Getter;

@Getter
public class LogoutRequest {
    private String accessToken;
    private String refreshToken;

    public LogoutRequest(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
