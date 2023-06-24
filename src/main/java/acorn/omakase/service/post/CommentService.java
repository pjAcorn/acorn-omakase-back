package acorn.omakase.service.post;

import acorn.omakase.domain.post.Comment;
import acorn.omakase.dto.commentDto.commentListDTO;
import acorn.omakase.dto.commentDto.modCommentRequest;
import acorn.omakase.dto.commentDto.NewCommentRequest;
import acorn.omakase.repository.CommentRepository;
import acorn.omakase.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final TokenProvider tokenProvider;

    @SneakyThrows
    public void addComment(NewCommentRequest newCommentRequest, String acTokenRequest) {
        String accessToken = acTokenRequest.substring(7);
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        String strUserId = authentication.getName();
        Long userId = Long.parseLong(strUserId);

        Comment saveComment = Comment.of(
                newCommentRequest.getCommentContent(), userId, newCommentRequest.getPostId());

        commentRepository.insertComment(saveComment);
    }

    public void modComment(modCommentRequest modCommentRequest) {
        Comment modComment = Comment.ofMod(
                modCommentRequest.getCommentId(), modCommentRequest.getContent());

        commentRepository.updateComment(modComment);
    }

    public void delComment(Long commentId) {
        commentRepository.deleteComment(commentId);
    }

    public void likeComment(Long commentId) {
        commentRepository.updateLikeComment(commentId);
    }

    public List<commentListDTO> commentList(Long postId) {
        return commentRepository.selectCommentList(postId);
    }

}
