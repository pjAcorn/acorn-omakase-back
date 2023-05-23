package acorn.omakase.repository;

import acorn.omakase.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {
    int insertComment(Map commentMap);

    int selectCommentNo();

    int updateLike(int comment_no);

    int updateComment(Map modCommentMap);

    int deleteComment(int comment_no);

    List selectCommentList(int post_no);
}
