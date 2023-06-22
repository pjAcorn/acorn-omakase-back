package acorn.omakase.repository;

import acorn.omakase.domain.post.Comment;
import acorn.omakase.dto.commentDto.commentListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
