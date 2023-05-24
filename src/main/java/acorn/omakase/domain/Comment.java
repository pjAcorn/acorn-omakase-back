package acorn.omakase.domain;

import lombok.*;

import java.sql.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    private Long commentId;
    private String content;
    private Long like;
    private Date createdAt;
    private String nickname;
    private Long postId;

    @Builder
    public Comment(Long commentId, String content, Long like, Date createdAt, String nickname, Long postId) {
        this.commentId = commentId;
        this.content = content;
        this.like = like;
        this.createdAt = createdAt;
        this.nickname = nickname;
        this.postId = postId;
    }

    public static Comment of(String content, String nickname, Long postId) {
        return Comment.builder()
                .content(content)
                .nickname(nickname)
                .postId(postId).build();
    }
}
