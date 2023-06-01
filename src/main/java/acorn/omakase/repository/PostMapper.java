package acorn.omakase.repository;

import acorn.omakase.domain.Post;
import acorn.omakase.dto.postdto.NewestPostDto;
import acorn.omakase.dto.postdto.SearchDto;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    void insertPost(Post savePost);

    void updatePost(Post modPost);

    void deletePost(Object postId);

    List<Post> selectPostView(Object postId);

    List<NewestPostDto> findPostListByNewest();

    int count(SearchDto params);

    List<Post> selectCategoryPostList(Object category);
}
