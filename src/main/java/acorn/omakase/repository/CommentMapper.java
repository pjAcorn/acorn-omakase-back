package acorn.omakase.repository;

import acorn.omakase.domain.Comment;
import acorn.omakase.dto.commentDto.commentListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    void insertComment(Comment saveComment);

    void updateComment(Comment modComment);

    void deleteComment(Long commentId);

    void updateLikeComment(Long commentId);

    List<commentListDTO> selectCommentList(Long postId);
}
