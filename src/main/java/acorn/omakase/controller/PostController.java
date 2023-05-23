package acorn.omakase.controller;

import acorn.omakase.domain.Comment;
import acorn.omakase.dto.WriteCommentDto;
import acorn.omakase.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class PostController {
    private final PostService postService;


//    새 댓글 쓰기
    @PostMapping("/write/new")
    public ResponseEntity addComment(@RequestBody WriteCommentDto commentDto){
//        Map commentMap = new HashMap();
//
//        commentMap.put("comment_content","저장테스트(원래는 본문에서 Comment 받아와야함)");
//        commentMap.put("user_nickname","테스트");
//        commentMap.put("post_no",1);

        // @modelattribute Comment comment
//        commentMap.put("comment_content",comment.getComment_content());
//        commentMap.put("user_nickname",comment.getUser_nickname());
//        commentMap.put("post_no",comment.getPost_no());

        postService.addComment(commentDto)

        return new ResponseEntity(HttpStatus.OK);
    }

//    댓글 좋아요
    @RequestMapping("/like_comment")
    public int likeComment(){
        // @requestparam int comment_no
        int comment_no = 1;

        int result = postService.likeComment(comment_no);

        return result;
    }

//    댓글 수정
    @RequestMapping("/mod_comment")
    public int modComment(){
        // @requestparam String comment_content
        // @requestparam int comment_no
        // 작성일도 만약에 바꿀거면 수정한 댓글은 수정된 댓글이라고 작게 쓰여지게 표시하기

        Map modCommentMap = new HashMap();

        modCommentMap.put("comment_content","수정합니당");
        modCommentMap.put("comment_no",1);

        int result = postService.modComment(modCommentMap);

        return result;
    }

//    댓글 삭제
    @RequestMapping("/del_comment")
    public int delComment(){
        // @requestparam int comment_no
        int comment_no = 1;

        int result = postService.delComment(comment_no);

        return result;
    }

//    댓글 보기(전체 post 보는 메서드에 이거 넣으면 될 듯?)
    public List boardComment(){
        // @RequestParam int post_no
        int post_no = 1;

        List commentList = postService.boardComment(post_no);

        return commentList;
    }
}
