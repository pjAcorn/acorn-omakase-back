package acorn.omakase.domain;

import lombok.*;

import java.sql.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    private int comment_no;
    private String comment_content;
    private int comment_like_cnt;
    private Date comment_date;
    private String user_nickname;
    private int post_no;

    @Builder
    public Comment(int comment_no, String comment_content, int comment_like_cnt, Date comment_date, String user_nickname, int post_no) {
        this.comment_no = comment_no;
        this.comment_content = comment_content;
        this.comment_like_cnt = comment_like_cnt;
        this.comment_date = comment_date;
        this.user_nickname = user_nickname;
        this.post_no = post_no;
    }

    public static Comment of(int comment_no, String comment_content, int comment_like_cnt, Date comment_date, String user_nickname, int post_no) {
        return Comment.builder()
                .comment_no(comment_no)
                .comment_content(comment_content)
                .comment_like_cnt(comment_like_cnt)
                .comment_date(comment_date)
                .user_nickname(user_nickname)
                .post_no(post_no).build();
    }
}
