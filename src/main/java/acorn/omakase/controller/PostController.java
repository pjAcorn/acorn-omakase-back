package acorn.omakase.controller;

import acorn.omakase.common.code.SuccessCode;
import acorn.omakase.common.response.ApiResponse;
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
    public ResponseEntity addPost(@RequestBody NewPostRequest newPostRequest,
                                  @RequestHeader(value = "Authorization") String acTokenRequest){
        postService.addPost(newPostRequest, acTokenRequest);

        return new ResponseEntity(new ApiResponse(SuccessCode.CREATE_SUCCESS), HttpStatus.CREATED);
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
        postService.addViews(postId);

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

    // 게시판 리스트 추천순
    @GetMapping("/like")
    public ResponseEntity likePostList(
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum
    ) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<likePostDto> likePostDtoPageInfo = PageInfo.of(postService.PostListByLike());

        return new ResponseEntity(likePostDtoPageInfo, HttpStatus.OK);
    }

    // 게시판 리스트 조회순
    @GetMapping("/view")
    public ResponseEntity viewPostList(
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum
    ) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<viewPostDto> viewPostDtoPageInfo = PageInfo.of(postService.PostListByView());

        return new ResponseEntity(viewPostDtoPageInfo, HttpStatus.OK);
    }

    // 게시물 좋아요
    @PatchMapping("/like/{postId}")
    public ResponseEntity likePost(@PathVariable Long postId){
        postService.likePost(postId);

        return new ResponseEntity(HttpStatus.OK);
    }

    // 게시판 제목 검색
    @GetMapping("/search/keyword")
    public ResponseEntity searchPost(
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
            @RequestBody Object keyword){

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<searchPostDto> searchPostDtoPageInfo = PageInfo.of(postService.searchPost(keyword));

        return new ResponseEntity(searchPostDtoPageInfo, HttpStatus.OK);
    }
}
