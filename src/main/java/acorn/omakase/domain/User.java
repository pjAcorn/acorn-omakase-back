package acorn.omakase.domain;

import acorn.omakase.dto.userdto.DeleteIdRequest;
import acorn.omakase.dto.userdto.FindIdRequest;
import acorn.omakase.dto.userdto.IdValidateRequest;
import acorn.omakase.dto.userdto.SignupRequest;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
<<<<<<< Updated upstream
public class User {
=======
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    private Long userId;
>>>>>>> Stashed changes

    private Long userId;
    private String loginId;
    private String name;
    private String password;
    private String email;
    private String region;
    private String nickname;

    @Builder
    public User(Long userId, String loginId, String name, String password, String email, String region, String nickname) {
        this.userId = userId;
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
                .email(findIdRequest.getEmail())
                .name(findIdRequest.getName()).build();
    }

    public static User of(IdValidateRequest idValidateRequest){
        return User.builder()
                .loginId(idValidateRequest.getLoginId()).build();
    }
}