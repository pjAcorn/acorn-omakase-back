package acorn.omakase.domain.user;

import acorn.omakase.dto.userdto.SignupRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;
    private String loginId;
    private String name;
    private String password;
    private String email;
    private String region;
    private String nickname;
//    private Role role;

    @Builder
    public User(Long userId, String loginId, String name, String password, String email, String region, String nickname) {
        this.userId = userId;
        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.region = region;
        this.nickname = nickname;
        this.role = Role.ROLE_USER;
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    public static User of(SignupRequest signupRequest) {
        return User.builder()
                .loginId(signupRequest.getLoginId())
                .nickname(signupRequest.getNickname())
                .password(signupRequest.getPassword())
                .email(signupRequest.getEmail())
                .name(signupRequest.getName())
                .region(signupRequest.getRegion()).build();
    }

    public void encodingPassword(String password) {
        this.password = password;
    }
}