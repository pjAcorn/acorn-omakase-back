package acorn.omakase.service;


import acorn.omakase.domain.Post;
import acorn.omakase.dto.postdto.*;
import acorn.omakase.repository.PostMapper;
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
    private final PostMapper postMapper;
    private final TokenProvider tokenProvider;

    @SneakyThrows
    public void addPost(NewPostRequest newPostRequest, String acTokenRequest) {
        String accessToken = acTokenRequest.substring(7);
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        String strUserId = authentication.getName();
        Long userId = Long.parseLong(strUserId);

        Post savePost = Post.ofNew(
                newPostRequest.getTitle(), newPostRequest.getContent(), userId, newPostRequest.getCategory());
        postMapper.insertPost(savePost);
    }

    public void modPost(modPostRequest post) {
        Post modPost = Post.ofMod(post.getPostId(), post.getTitle(), post.getContent(), post.getCategory());

        postMapper.updatePost(modPost);
    }

    public void delPost(Object postId) {
        postMapper.deletePost(postId);
    }

    public PostResponse viewPost(Long postId) {
        PostResponse post = postMapper.findById(postId);
        if (post == null) {
            throw new IllegalStateException("게시물이 없습니다.");
        }

        return post;
    }

    public List<NewestPostDto> PostListByNewest() {

        return postMapper.findPostListByNewest();
    }

    public List<Post> listCategoryPost(Object category) {
        return postMapper.selectCategoryPostList(category);
    }
}
