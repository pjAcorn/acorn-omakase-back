package acorn.omakase.service.post;


import acorn.omakase.domain.post.Post;
import acorn.omakase.dto.postdto.*;
import acorn.omakase.repository.PostRepository;
import acorn.omakase.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final TokenProvider tokenProvider;

    @SneakyThrows
    public void addPost(NewPostRequest newPostRequest, String acTokenRequest) {
        String accessToken = acTokenRequest.substring(7);
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        String strUserId = authentication.getName();
        Long userId = Long.parseLong(strUserId);

        Post savePost = Post.of(
                newPostRequest.getTitle(), newPostRequest.getContent(), userId, newPostRequest.getCategory());
        postRepository.insertPost(savePost);
    }

    public void modPost(modPostRequest post) {
        Post modPost = Post.ofMod(post.getPostId(), post.getTitle(), post.getContent(), post.getCategory());

        postRepository.updatePost(modPost);
    }

    public void delPost(Object postId) {
        postRepository.deletePost(postId);
    }

    public PostResponse viewPost(Long postId) {
        PostResponse post = postRepository.findById(postId);
        if (post == null) {
            throw new IllegalStateException("게시물이 없습니다.");
        }

        return post;
    }

    public List<NewestPostDto> PostListByNewest() {

        return postRepository.findPostListByNewest();
    }

    public List<likePostDto> PostListByLike() {

        return postRepository.findPostListByLike();
    }

    public List<viewPostDto> PostListByView() {

        return postRepository.findPostListByView();
    }

    public List<Post> listCategoryPost(Object category) {
        return postRepository.selectCategoryPostList(category);
    }

    public void likePost(Long postId) {
        postRepository.updateLikePost(postId);
    }

    public void addViews(Long postId) {
        postRepository.updateViews(postId);
    }

    public List<searchPostDto> searchPost(Object keyword) {
        return postRepository.findByKeyword(keyword);
    }
}
