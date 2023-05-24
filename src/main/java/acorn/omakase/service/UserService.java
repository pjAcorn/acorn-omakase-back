package acorn.omakase.service;

import acorn.omakase.domain.User;
import acorn.omakase.dto.userdto.FindIdRequest;
import acorn.omakase.dto.userdto.SignupRequest;
import acorn.omakase.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public List<User> getUserList() {
        System.out.println("test");
        System.out.println("test2");
        System.out.println("test3");
        System.out.println("test4");
        System.out.println("test5");
        return userMapper.getUserList();
    }

    public void signup(SignupRequest signupRequest) {

        User user = User.of(signupRequest);
        userMapper.signup(user);
    }

    // 아이디 찾기
    public String findId(FindIdRequest findIdRequest){
        User findId = User.of(findIdRequest);
        String id = userMapper.findid(findId);
        if(id== null){
            throw new IllegalStateException("찾는 아이디가 없습니다.");
        }
        return id;
    }
}