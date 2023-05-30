package acorn.omakase.repository;

import acorn.omakase.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    void insertPost(Post savePost);

    void updatePost(Post modPost);

    void deletePost(Object postId);

    List<Post> selectPostView(Object postId);

    List<Post> selectPostList();

    List<Post> selectCategoryPostList(Object category);
}
