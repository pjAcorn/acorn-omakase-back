package acorn.omakase.controller;

import acorn.omakase.dto.modCommentDTO;
import acorn.omakase.dto.newCommentDTO;
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
    public ResponseEntity newComment(@RequestBody newCommentDTO comment){

        commentService.newComment(comment);

        return new ResponseEntity(HttpStatus.CREATED);
    }

//    댓글 좋아요
    @PutMapping("/like_comment")
    public ResponseEntity likeComment(@RequestBody int comment_no){

        commentService.likeComment(comment_no);

        return new ResponseEntity(HttpStatus.OK);
    }

//    댓글 수정
    @PutMapping("/mod_comment")
    public ResponseEntity modComment(@RequestBody modCommentDTO comment){

        commentService.modComment(comment);

        return new ResponseEntity(HttpStatus.OK);
    }

//    댓글 삭제
    @DeleteMapping("/del_comment")
    public ResponseEntity delComment(@RequestBody int comment_no){
        commentService.delComment(comment_no);

        return new ResponseEntity(HttpStatus.OK);
    }

//    댓글 보기(post 보는 메서드에 이거 넣으면 될 듯? 겟매핑 해줘야 함)
    public ResponseEntity boardComment(@RequestBody int post_no){
        List commentList = commentService.boardComment(post_no);

        return new ResponseEntity(commentList, HttpStatus.OK);
    }
}
