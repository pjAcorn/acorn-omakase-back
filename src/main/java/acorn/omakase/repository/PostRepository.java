package acorn.omakase.repository;

import acorn.omakase.domain.post.Post;
import acorn.omakase.dto.postdto.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
