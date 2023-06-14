package acorn.omakase.controller;

import acorn.omakase.dto.commentDto.modCommentRequest;
import acorn.omakase.dto.commentDto.newCommentRequest;
import acorn.omakase.service.post.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

//    새 댓글 쓰기
    @PostMapping("/new")
    public ResponseEntity addComment(@RequestBody newCommentRequest newCommentRequest){
        commentService.addComment(newCommentRequest);

        return new ResponseEntity(HttpStatus.OK);
    }

//    댓글 수정
    @PatchMapping("/mod")
    public ResponseEntity modComment(@RequestBody modCommentRequest modCommentRequest){
        commentService.modComment(modCommentRequest);

        return new ResponseEntity(HttpStatus.OK);
    }

//    댓글 삭제
    @DeleteMapping("/del/{commentId}")
    public ResponseEntity delComment(@PathVariable Long commentId){
        commentService.delComment(commentId);

        return new ResponseEntity(HttpStatus.OK);
    }

//    댓글 좋아요
    @PatchMapping("/like/{commentId}")
    public ResponseEntity likeComment(@PathVariable Long commentId){
        commentService.likeComment(commentId);

        return new ResponseEntity(HttpStatus.OK);
    }

//    @GetMapping("/list/{postId}")
//    public ResponseEntity commentList(
//            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
//            @RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
//            @PathVariable Long postId
//    ){
//        PageHelper.startPage(pageNum, pageSize);
//        PageInfo<commentListDTO> commentListDTOPageInfo = PageInfo.of(commentService.commentList(postId));
//
//        return new ResponseEntity(commentListDTOPageInfo, HttpStatus.OK);
//    }

}
