package acorn.omakase.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private Long id;
    private String user_id;
    private String user_name;
    private String user_pw;
    private String user_email;
    private String user_region;
    private String user_nickname;
}