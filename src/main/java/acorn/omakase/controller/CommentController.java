package acorn.omakase.controller;

import acorn.omakase.domain.Comment;
import acorn.omakase.dto.commentDto.modCommentDTO;
import acorn.omakase.dto.commentDto.newCommentRequest;
import acorn.omakase.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

//    새 댓글 쓰기
    @PostMapping("/new_comment")
    public ResponseEntity newComment(@RequestBody newCommentRequest comment){

        commentService.newComment(comment);

        return new ResponseEntity(HttpStatus.CREATED);
    }

//    댓글 좋아요
    @PatchMapping("/like_comment")
    public ResponseEntity likeComment(@RequestBody Object comment_no){
        commentService.likeComment(comment_no);

        return new ResponseEntity(HttpStatus.OK);
    }

//    댓글 수정
    @PatchMapping("/mod_comment")
    public ResponseEntity modComment(@RequestBody modCommentDTO comment){

        commentService.modComment(comment);

        return new ResponseEntity(HttpStatus.OK);
    }

//    댓글 삭제
    @DeleteMapping("/del_comment")
    public ResponseEntity delComment(@RequestBody Object comment_no){
        commentService.delComment(comment_no);

        return new ResponseEntity(HttpStatus.OK);
    }

//    댓글 보기(post 보는 메서드에 이거 넣으면 될 듯? 겟매핑은 post에서 해줘야 함)
    @GetMapping("/view_comment")
    public ResponseEntity viewComment(@RequestBody Object post_no){
        List<Comment> commentList = commentService.viewComment(post_no);

        return new ResponseEntity(commentList, HttpStatus.OK);
    }
}
