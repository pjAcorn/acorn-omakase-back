package acorn.omakase.dto.userdto;


import acorn.omakase.token.dto.TokenResponse;
import lombok.Getter;

@Getter
public class LoginResponse {

    private Long userId;
    private String nickname;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;

    public LoginResponse(Long userId, String nickname, TokenResponse tokenResponse) {
        this.userId = userId;
        this.nickname = nickname;
        this.accessToken = tokenResponse.getAccessToken();
        this.refreshToken = tokenResponse. getRefreshToken();
        this.accessTokenExpiresIn = tokenResponse.getAccessTokenExpiresIn();
    }
}