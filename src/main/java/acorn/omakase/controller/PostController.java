package acorn.omakase.controller;

import acorn.omakase.domain.Comment;
import acorn.omakase.domain.Post;
import acorn.omakase.dto.postdto.*;
import acorn.omakase.service.CommentService;
import acorn.omakase.service.PostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    // 새 글 쓰기
    @PostMapping("/new")
    public ResponseEntity addPost(@RequestBody NewPostRequest newPostRequest){
        postService.addPost(newPostRequest);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    // 글 수정
    @PatchMapping("/mod_post")
    public ResponseEntity modPost(@RequestBody modPostRequest post){
        postService.modPost(post);

        return new ResponseEntity(HttpStatus.OK);
    }
    // 글 삭제
    @DeleteMapping("/del_post")
    public ResponseEntity delPost(@RequestBody Object postId){
        postService.delPost(postId);

        return new ResponseEntity(HttpStatus.OK);
    }

    // 게시판 뷰
    @GetMapping("/view_post")
    public ResponseEntity viewPost(@RequestBody Object postId){
        List<Post> postView = postService.viewPost(postId);
        List<Comment> commentList = commentService.viewComment(postId);

        Map postNcomment = new HashMap();
        postNcomment.put("post", postView);
        postNcomment.put("comment", commentList);

        return new ResponseEntity(postNcomment, HttpStatus.OK);
    }

    // 게시판 리스트 최신순
    @GetMapping("/newest")
    public ResponseEntity newestPostList(
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum
    ){
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<NewestPostDto> newestPostDtoPageInfo = PageInfo.of(postService.PostListByNewest());
        return new ResponseEntity(newestPostDtoPageInfo, HttpStatus.OK);
    }

    // 카테고리 별 게시판 리스트(매핑 잘 모르겟다)
    @GetMapping("/list_category")
    public ResponseEntity listCategoryPost(@RequestBody Object category){
        List<Post> categoryPostList = postService.listCategoryPost(category);

        return new ResponseEntity(categoryPostList, HttpStatus.OK);
    }
}
