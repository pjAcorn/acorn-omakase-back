package acorn.omakase.service;

import acorn.omakase.domain.User;
import acorn.omakase.dto.userdto.DeleteIdRequest;
import acorn.omakase.dto.userdto.FindIdRequest;
import acorn.omakase.dto.userdto.HomeResponse;
import acorn.omakase.dto.userdto.LoginRequest;
import acorn.omakase.dto.userdto.SignupRequest;
import acorn.omakase.repository.UserMapper;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserMapper userMapper;

    public List<User> getUserList() {

        return userMapper.getUserList();
    }

    public void signup(SignupRequest signupRequest) {

        User user = User.of(signupRequest);
        userMapper.signup(user);
    }

    // 아이디 찾기
    public String findId(FindIdRequest findIdRequest){

        String id = userMapper.findId(findIdRequest);

        User findId = User.of(findIdRequest);
       

        if(id== null){
            throw new IllegalStateException("찾는 아이디가 없습니다.");
        }
        log.info("test");
        return id;
    }

    public User login(LoginRequest loginRequest) throws Exception {
        User userId = userMapper.login(loginRequest);

        if(userId==null){
             throw new Exception("아이디/비밀번호가 일치하지 않습니다.");
        }
        return userId;
    }


    // 회원 탈퇴
    public void delete(DeleteIdRequest deleteIdRequest){
        DeleteIdRequest deleteUser = deleteIdRequest;

        Long userId = deleteUser.getUserId();
        // 회원 암호 가져오기
        String password = userMapper.getPw(userId);
        // 첫번째 입력
        String password1 = deleteIdRequest.getPassword1();
        // 두번째 입력
        String password2 = deleteIdRequest.getPassword2();
        if(password1.equals(password2 )){
            if(password1.equals(password)){
                userMapper.deleteId(userId);
            }else {
                throw new IllegalStateException("비밀번호가 회원 정보와 틀립니다.");
            }
        }else{
             throw new IllegalStateException("입력한 두 비밀번호가 일치하지 않습니다.");
        }
    }

}