package acorn.omakase.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private Date createdAt;
    private String category;

    @Builder
    public Post(Long postId, String title, String content,Long userId, Date createdAt, String category){
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
        this.category = category;
    }

    public static Post ofNew(String title, String content, Long userId, String category){
        return Post.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .category(category).build();
    }

    public static Post ofMod(long postId,String title, String content, String category){
        return Post.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .category(category).build();
    }
}
