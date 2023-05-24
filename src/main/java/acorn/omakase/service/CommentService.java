package acorn.omakase.service;

import acorn.omakase.domain.Comment;
import acorn.omakase.dto.commentDto.modCommentRequest;
import acorn.omakase.dto.commentDto.newCommentRequest;
import acorn.omakase.repository.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentMapper commentMapper;

    public void newComment(newCommentRequest comment) {
        Comment saveComment = Comment.ofNew(comment.getContent(), comment.getNickname(), comment.getPostId());

        commentMapper.insertComment(saveComment);
    }

    public void likeComment(Object commentId) {
        commentMapper.updateLike(commentId);
    }

    public void modComment(modCommentRequest comment) {
        Comment modComment = Comment.ofMod(comment.getCommentId(), comment.getContent());

        commentMapper.updateComment(modComment);
    }

    public void delComment(Object commentId) {
        commentMapper.deleteComment(commentId);
    }

    public List<Comment> viewComment(Object postId) {
        return commentMapper.selectCommentList(postId);
    }
}
