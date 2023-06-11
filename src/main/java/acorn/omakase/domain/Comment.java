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
    private Long userId;
    private Long postId;
    private Date updatedAt;

    @Builder
    public Comment(Long commentId, String content, Long likeCount, Date createdAt, Long userId, Long postId, Date updatedAt) {
        this.commentId = commentId;
        this.content = content;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.userId = userId;
        this.postId = postId;
        this.updatedAt = updatedAt;
    }

    public static Comment ofNew(String content, Long userId, Long postId) {
        return Comment.builder()
                .content(content)
                .userId(userId)
                .postId(postId).build();
    }

    public static Comment ofMod(Long commentId, String content) {
        return Comment.builder()
                .commentId(commentId)
                .content(content).build();
    }
    
}
