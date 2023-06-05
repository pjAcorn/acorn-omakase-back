package acorn.omakase.service;

import acorn.omakase.domain.User;
import acorn.omakase.dto.userdto.*;
import acorn.omakase.repository.UserMapper;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final RedisUtil redisUtil;
    private final UserMapper userMapper;

    public List<User> getUserList() {

        return userMapper.getUserList();
    }

    public void signup(SignupRequest signupRequest) {

        User user = User.of(signupRequest);
        userMapper.signup(user);
    }

    // 아이디 찾기
    public String findId(FindIdRequest findIdRequest) {

        String email = findIdRequest.getEmail();
        String redisEmail = redisUtil.getData(findIdRequest.getCode());

        if (!email.equals(redisEmail)){
            throw new IllegalStateException("인증번호 불일치");
        }
        String id = userMapper.findId(findIdRequest);

        return id;
    }

    // 비밀번호 찾기
    public String findPw(FindPwRequest findPwRequest){
        String email = findPwRequest.getEmail();
        String redisEmail = redisUtil.getData(findPwRequest.getCode());

        if(!email.equals(redisEmail)){
            throw new IllegalStateException("인증번호 불일치");
        }
        //findPw 가 아닌 password 변경 로직이 더 좋을 것 같다.
        String pw = userMapper.findPw(findPwRequest);

        return pw;
    }

    public User login(LoginRequest loginRequest) throws Exception {
        User userId = userMapper.login(loginRequest);

        if (userId == null) {
            throw new Exception("아이디/비밀번호가 일치하지 않습니다.");
        }
        return userId;
    }


    // 회원 탈퇴
    public void delete(DeleteIdRequest deleteIdRequest) {
        DeleteIdRequest deleteUser = deleteIdRequest;

        Long userId = deleteUser.getUserId();
        // 회원 암호 가져오기
        String password = userMapper.getPw(userId);
        // 첫번째 입력
        String password1 = deleteIdRequest.getPassword1();
        // 두번째 입력
        String password2 = deleteIdRequest.getPassword2();
        if (password1.equals(password2)) {
            if (password1.equals(password)) {
                userMapper.deleteId(userId);
            } else {
                throw new IllegalStateException("비밀번호가 회원 정보와 틀립니다.");
            }
        } else {
            throw new IllegalStateException("입력한 두 비밀번호가 일치하지 않습니다.");
        }
    }

    // 아이디 중복 확인
    public void idValidate(IdValidateRequest idValidateRequest) {
        User idChk = User.of(idValidateRequest);

        int check = userMapper.idValidate(idChk);

        if (check > 0) {
            throw new IllegalStateException("중복된 아이디 입니다.");
        }
    }



}