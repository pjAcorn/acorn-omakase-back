package acorn.omakase.service;


import acorn.omakase.domain.Post;
import acorn.omakase.dto.postdto.modPostRequest;
import acorn.omakase.dto.postdto.newPostRequest;
import acorn.omakase.repository.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostMapper postMapper;

    public void addPost(newPostRequest post) {
        Post savePost = Post.ofNew(post.getTitle(), post.getContent(), post.getNickname(), post.getCategory());

        postMapper.insertPost(savePost);
    }

    public void modPost(modPostRequest post) {
        Post modPost = Post.ofMod(post.getPostId(), post.getTitle(), post.getContent(), post.getCategory());

        postMapper.updatePost(modPost);
    }

    public void delPost(Object postId) {
        postMapper.deletePost(postId);
    }

    public List<Post> viewPost(Object postId) {
        return postMapper.selectPostView(postId);
    }

    public List<Post> listPost() {
        return postMapper.selectPostList();
    }

    public List<Post> listCategoryPost(Object category) {
        return postMapper.selectCategoryPostList(category);
    }
}
