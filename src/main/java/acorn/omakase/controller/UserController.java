package acorn.omakase.controller;

import acorn.omakase.domain.User;
import acorn.omakase.dto.userdto.*;
import acorn.omakase.service.user.EmailService;
import acorn.omakase.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/userlist")
    public ResponseEntity getUserList() {
        List<User> userList = userService.getUserList();
        return new ResponseEntity(userList, HttpStatus.OK);
    }


    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignupRequest signupRequest) {
        userService.signup(signupRequest);

        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("/find/id")
    public ResponseEntity findId(@RequestBody FindIdRequest findIdRequest){
        String id = userService.findId(findIdRequest);

        return new ResponseEntity(id, HttpStatus.OK);
    }

    // 비밀번호 찾기
    @PostMapping("/find/pw")
    public ResponseEntity findPw(@RequestBody FindPwRequest findPwRequest){
        userService.findPw(findPwRequest);

        return new ResponseEntity(HttpStatus.OK);
    }

<<<<<<< Updated upstream

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) throws Exception {
        String userId = userService.login(loginRequest);

        return new ResponseEntity(userId, HttpStatus.OK);
    }
=======
//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody LoginRequest loginRequest) throws Exception {
//        User userId = userService.login(loginRequest);
//
//        return new ResponseEntity(userId, HttpStatus.OK);
//    }

    // 로그인 ( 토큰 발급 )
    @PostMapping("/login")
    public TokenInfo login(@RequestBody LoginRequest loginRequest) {

        String loginId = loginRequest.getLoginId();
        String password = loginRequest.getPassword();
        TokenInfo tokenInfo = memberService.login(loginId, password);
        return tokenInfo;
}
>>>>>>> Stashed changes

    // 회원탈퇴
    @PostMapping("/delete")
    public ResponseEntity delete(@RequestBody DeleteIdRequest deleteIdRequest){
        userService.delete(deleteIdRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 아이디 중복 확인
    @PostMapping("/idValidation")
    public ResponseEntity IdValidation(@RequestBody IdValidateRequest idValidateRequest){
        userService.idValidate(idValidateRequest);

        return new ResponseEntity(HttpStatus.OK);
    }

<<<<<<< Updated upstream
    private final EmailService emailService;

    // 이메일 인증
    @PostMapping("/login/mailConfirm")
    public String mailConfirm(@RequestBody EmailAuthRequestDto emailDto) throws MessagingException, UnsupportedEncodingException {

        String authCode = emailService.sendEmail(emailDto.getEmail());
        // 인증코드를 그대로 반환하는거?
        return authCode;
=======
    // 이메일 인증
    @PostMapping("/login/mailConfirm")
    public ResponseEntity mailConfirm(@RequestBody EmailAuthRequestDto emailDto) throws MessagingException, UnsupportedEncodingException {

        emailService.sendEmail(emailDto.getEmail());
        return new ResponseEntity(HttpStatus.OK);
>>>>>>> Stashed changes
    }

    // 비밀번호 재설정
    @PatchMapping("/modify/pw")
    public ResponseEntity resetPw(@RequestBody ResetPwRequest resetPwRequest){
        userService.resetPw(resetPwRequest);

        return new ResponseEntity<String>("비밀번호 변경 완료", HttpStatus.OK);
    }
}

