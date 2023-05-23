package acorn.omakase.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class Comment {
    private int comment_no;
    private String comment_content;
    private int comment_like_cnt;
    private Date comment_date;
    private String user_nickname;
    private int post_no;
}
