package acorn.omakase.repository;

import acorn.omakase.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    void insertComment(Comment toComment);

    void updateLike(int comment_no);

    void updateComment(Comment toComment);

    void deleteComment(int comment_no);

    List selectCommentList(int post_no);
}
