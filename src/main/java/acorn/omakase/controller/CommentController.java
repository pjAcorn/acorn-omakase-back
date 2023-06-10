package acorn.omakase.controller;

import acorn.omakase.domain.Comment;
import acorn.omakase.dto.commentDto.modCommentRequest;
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
    public ResponseEntity likeComment(@RequestBody Object commentId){
        commentService.likeComment(commentId);

        return new ResponseEntity(HttpStatus.OK);
    }

//    댓글 수정
    @PatchMapping("/mod_comment")
    public ResponseEntity modComment(@RequestBody modCommentRequest comment){
        System.out.println("test");
        //commentService.modComment(comment);

        return new ResponseEntity(HttpStatus.OK);
    }

//    댓글 삭제
    @DeleteMapping("/del_comment")
    public ResponseEntity delComment(@RequestBody Object commentId){
        commentService.delComment(commentId);

        return new ResponseEntity(HttpStatus.OK);
    }

//    댓글 보기(post에 매핑해놓음)
    @GetMapping
    public ResponseEntity viewComment(@RequestBody Object postId){
        List<Comment> commentList = commentService.viewComment(postId);

        return new ResponseEntity(commentList, HttpStatus.OK);
    }
}
