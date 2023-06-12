package acorn.omakase.service.post;

import acorn.omakase.domain.Comment;
import acorn.omakase.dto.commentDto.commentListDTO;
import acorn.omakase.dto.commentDto.modCommentRequest;
import acorn.omakase.dto.commentDto.newCommentRequest;
import acorn.omakase.dto.postdto.NewestPostDto;
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

    public void addComment(newCommentRequest newCommentRequest) {
        Comment saveComment = Comment.ofNew(
                newCommentRequest.getContent(), newCommentRequest.getUserId(), newCommentRequest.getPostId());

        commentMapper.insertComment(saveComment);
    }

    public void modComment(modCommentRequest modCommentRequest) {
        Comment modComment = Comment.ofMod(
                modCommentRequest.getCommentId(), modCommentRequest.getContent());

        commentMapper.updateComment(modComment);
    }

    public void delComment(Long commentId) {
        commentMapper.deleteComment(commentId);
    }

    public void likeComment(Long commentId) {
        commentMapper.updateLikeComment(commentId);
    }

    public List<commentListDTO> commentList(Long postId) {
        return commentMapper.selectCommentList(postId);
    }

}
