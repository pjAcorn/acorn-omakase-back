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
    private String title;
    private String content;
    private String nickname;
    private Date createdAt;
    private String category;

    @Builder
    public Post(Long postId, String title, String content, String nickname, Date createdAt, String category){
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.category = category;
    }

    public static Post ofNew(String title, String content, String nickname, String category){
        return Post.builder()
                .title(title)
                .content(content)
                .nickname(nickname)
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
