package acorn.omakase.service;

import acorn.omakase.domain.Comment;
import acorn.omakase.repository.PostMapper;
import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;

    public int addComment(Map commentMap) {
        int comment_no = selectCommentNo();
        commentMap.put("comment_no", comment_no);

        return postMapper.insertComment(commentMap);
    }

    private int selectCommentNo() {
        Integer result = postMapper.selectCommentNo();

        if(result == null){
            result = 1;
        }

        return result;
    }

    public int likeComment(int comment_no) {
        return postMapper.updateLike(comment_no);
    }

    public int modComment(Map modCommentMap) {
        return postMapper.updateComment(modCommentMap);
    }

    public int delComment(int comment_no) {
        return postMapper.deleteComment(comment_no);
    }

    public List boardComment(int post_no) {
        return postMapper.selectCommentList(post_no);
    }
}
