package acorn.omakase.repository;

import acorn.omakase.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    void insertComment(Comment savecomment);

    void updateLike(Object commentId);

    void updateComment(Comment modComment);

    void deleteComment(Object commentId);

    List<Comment> selectCommentList(Object postId);
}
