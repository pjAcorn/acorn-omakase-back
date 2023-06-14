package acorn.omakase.controller;

import acorn.omakase.domain.Post;
import acorn.omakase.dto.commentDto.commentListDTO;
import acorn.omakase.dto.postdto.*;
import acorn.omakase.service.PostService;
import acorn.omakase.service.post.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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
    @PatchMapping("/mod")
    public ResponseEntity modPost(@RequestBody modPostRequest post){
        postService.modPost(post);

        return new ResponseEntity(HttpStatus.OK);
    }
    // 글 삭제
    @DeleteMapping("/del")
    public ResponseEntity delPost(@RequestBody Object postId){
        postService.delPost(postId);

        return new ResponseEntity(HttpStatus.OK);
    }

    // 게시판 뷰
    @GetMapping("/{postId}")
    public ResponseEntity viewPost(
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
            @PathVariable Long postId
    ){
        PostResponse postResponse = postService.viewPost(postId);

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<commentListDTO> commentListDTOPageInfo = PageInfo.of(commentService.commentList(postId));

        List comment = new ArrayList();
        for(int i=0;i<commentListDTOPageInfo.getSize();i++){
            comment.add(i, commentListDTOPageInfo.getList().get(i));
        }

        PostViewResponse postViewResponse = new PostViewResponse(postResponse, comment);

        return new ResponseEntity(postViewResponse, HttpStatus.OK);
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


//    @GetMapping("/list_category")
//    public ResponseEntity listCategoryPost(@RequestBody Object category){
//        List<Post> categoryPostList = postService.listCategoryPost(category);
//
//        return new ResponseEntity(categoryPostList, HttpStatus.OK);
//    }
}
