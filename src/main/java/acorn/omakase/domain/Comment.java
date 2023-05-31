package acorn.omakase.domain;

import lombok.*;

import java.sql.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    private Long commentId;
    private String content;
    private Long likeCount;
    private Date createdAt;
    private String nickname;
    private Long postId;

    @Builder
    public Comment(Long commentId, String content, Long likeCount, Date createdAt, String nickname, Long postId) {
        this.commentId = commentId;
        this.content = content;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.nickname = nickname;
        this.postId = postId;
    }

    public static Comment ofNew(String content, String nickname, Long postId) {
        return Comment.builder()
                .content(content)
                .nickname(nickname)
                .postId(postId).build();
    }

    public static Comment ofMod(Long commentId, String content) {
        return Comment.builder()
                .commentId(commentId)
                .content(content).build();
    }
    
}
