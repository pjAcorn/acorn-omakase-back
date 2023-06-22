package acorn.omakase.domain.post;

import lombok.*;

import java.sql.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    private Long commentId;
    private String commentContent;
    private Long likeCount;
    private Date createdAt;
    private Long userId;
    private Long postId;
    private Date updatedAt;

    @Builder
    public Comment(Long commentId, String commentContent, Long likeCount, Date createdAt, Long userId, Long postId, Date updatedAt) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.userId = userId;
        this.postId = postId;
        this.updatedAt = updatedAt;
    }

    public static Comment ofNew(String commentContent, Long userId, Long postId) {
        return Comment.builder()
                .commentContent(commentContent)
                .userId(userId)
                .postId(postId).build();
    }

    public static Comment ofMod(Long commentId, String commentContent) {
        return Comment.builder()
                .commentId(commentId)
                .commentContent(commentContent).build();
    }
    
}
