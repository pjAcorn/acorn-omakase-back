package acorn.omakase.repository;

import acorn.omakase.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    void insertComment(Comment toComment);

    void updateLike(Object comment_no);

    void updateComment(Comment toComment);

    void deleteComment(Object comment_no);

    List<Comment> selectCommentList(Object post_no);
}
