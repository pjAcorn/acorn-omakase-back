package acorn.omakase.repository;

import acorn.omakase.domain.Post;
import acorn.omakase.dto.postdto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    void insertPost(Post savePost);

    void updatePost(Post modPost);

    void deletePost(Object postId);

    PostResponse findById(Long postId);

    List<NewestPostDto> findPostListByNewest();

    int count(SearchDto params);

    List<Post> selectCategoryPostList(Object category);

    void updateLikePost(Long postId);

    List<likePostDto> findPostListByLike();

    List<viewPostDto> findPostListByView();

    void updateViews(Long postId);

    List<searchPostDto> findByKeyword(String keyword);
}
