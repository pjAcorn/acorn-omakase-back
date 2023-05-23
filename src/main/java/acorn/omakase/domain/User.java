package acorn.omakase.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private Long userId;
    private String loginId;
    private String userName;
    private String userPw;
    private String userEmail;
    private String userRegion;
    private String userNickName;

}