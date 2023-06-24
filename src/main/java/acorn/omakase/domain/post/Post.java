package acorn.omakase.domain.post;

import acorn.omakase.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "POST")
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    private String title;
    private String content;
    private Long viewCount;
    private String category;

    @CreatedDate
    @LastModifiedDate
    private LocalDateTime createdAt;


    @Builder
    public Post(Long id, String title, String content, User user, LocalDateTime createdAt, String category){
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
        this.category = category;
    }

    public static Post of(String title, String content, User user, String category){
        return Post.builder()
                .title(title)
                .content(content)
                .user(user)
                .category(category).build();
    }

    public void addViewCount() {
        this.viewCount++;
    }


}
