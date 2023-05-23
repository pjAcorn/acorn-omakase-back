package acorn.omakase.service;

import acorn.omakase.domain.Comment;
import acorn.omakase.dto.modCommentDTO;
import acorn.omakase.dto.newCommentDTO;
import acorn.omakase.repository.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;

    public void newComment(newCommentDTO comment) {
       Comment toComment = Comment.of(comment.getComment_no(), comment.getComment_content(), comment.getComment_like_cnt()
                , comment.getComment_date(), comment.getUser_nickname(), comment.getPost_no());

        commentMapper.insertComment(toComment);
    }

    public void likeComment(Object comment_no) {
        commentMapper.updateLike(comment_no);
    }

    public void modComment(modCommentDTO comment) {
        Comment toComment = Comment.of(comment.getComment_no(), comment.getComment_content(), comment.getComment_like_cnt()
                , comment.getComment_date(), comment.getUser_nickname(), comment.getPost_no());

        commentMapper.updateComment(toComment);
    }

    public void delComment(Object comment_no) {
        commentMapper.deleteComment(comment_no);
    }

    public List<Comment> viewComment(Object post_no) {
        return commentMapper.selectCommentList(post_no);
    }
}
