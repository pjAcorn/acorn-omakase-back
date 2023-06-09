package acorn.omakase.domain;

import acorn.omakase.dto.userdto.FindIdRequest;
import acorn.omakase.dto.userdto.IdValidateRequest;
import acorn.omakase.dto.userdto.SignupRequest;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
    @Id
    private Long userId;

    private String loginId;
    private String name;
    private String password;
    private String email;
    private String region;
    private String nickname;


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

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}