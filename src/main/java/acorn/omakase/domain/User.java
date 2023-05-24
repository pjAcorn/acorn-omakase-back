package acorn.omakase.domain;

import acorn.omakase.dto.userdto.FindIdRequest;
import acorn.omakase.dto.userdto.SignupRequest;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private Long userId;
    private String loginId;
    private String name;
    private String password;
    private String email;
    private String region;
    private String nickname;

    @Builder
    public User(String loginId, String name, String password, String email, String region, String nickname) {

        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.region = region;
        this.nickname = nickname;
    }

    public static User of(SignupRequest signupRequest) {
        return User.builder()
                .loginId(signupRequest.getLoginId())
                .nickname(signupRequest.getNickname())
                .password(signupRequest.getPassword())
                .email(signupRequest.getEmail())
                .name(signupRequest.getName())
                .region(signupRequest.getRegion()).build();
    }

    public static User of(FindIdRequest findIdRequest) {
        return User.builder()
                .loginId(findIdRequest.getLoginId())
                .email(findIdRequest.getEmail())
                .name(findIdRequest.getName()).build();
    }

}