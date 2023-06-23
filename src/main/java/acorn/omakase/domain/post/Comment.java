package acorn.omakase.domain.post;

import acorn.omakase.domain.user.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Entity
@EntityListeners(EntityListeners.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    private String commentContent;
    private Long likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public Comment(Long id, String commentContent, Long likeCount, LocalDateTime createdAt, User user, Post post, LocalDateTime updatedAt) {
        this.id = id;
        this.commentContent = commentContent;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.user = user;
        this.post = post;
        this.updatedAt = updatedAt;
    }

    public static Comment of(String commentContent, User user, Post post) {
        return Comment.builder()
                .commentContent(commentContent)
                .user(user)
                .post(post).build();
    }
}
