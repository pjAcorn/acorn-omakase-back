package acorn.omakase.repository;

import acorn.omakase.domain.Post;
import acorn.omakase.dto.postdto.NewestPostDto;
import acorn.omakase.dto.postdto.PostResponse;
import acorn.omakase.dto.postdto.SearchDto;
import com.github.pagehelper.Page;
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

}
