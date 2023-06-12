package acorn.omakase.controller;

import acorn.omakase.common.code.SuccessCode;
import acorn.omakase.common.response.ApiResponse;
import acorn.omakase.domain.User;
import acorn.omakase.dto.userdto.*;
import acorn.omakase.service.user.EmailService;
import acorn.omakase.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Transactional
public class UserController {

    private final UserService userService;

    @GetMapping("/userlist")
    public ResponseEntity getUserList() {
        List<User> userList = userService.getUserList();
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignupRequest signupRequest) {
        userService.signup(signupRequest);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "아이디 찾기")
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

    // 로그인
    @PostMapping("/login")
<<<<<<< Updated upstream
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) throws Exception {
        User userId = userService.login(loginRequest);
=======
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = userService.login(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
>>>>>>> Stashed changes

    // 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<ApiResponse> logout(
            @RequestHeader(value = "Authorization") String acTokenRequest,
            @RequestHeader(value = "RefreshToken") String rfTokenRequest
    ) throws Exception {
        String accessToken = acTokenRequest.substring(7);
        String refreshToken = rfTokenRequest.substring(7);
        userService.logout(accessToken, refreshToken);

        return new ResponseEntity<>(new ApiResponse(SuccessCode.LOGOUT), HttpStatus.OK);
    }

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

    private final EmailService emailService;

    // 이메일 인증
    @PostMapping("/login/mailConfirm")
    public ResponseEntity mailConfirm(@RequestBody EmailAuthRequestDto emailDto) throws MessagingException, UnsupportedEncodingException {

        emailService.sendEmail(emailDto.getEmail());
        // 인증코드를 그대로 반환하는거?
        return new ResponseEntity(HttpStatus.OK);
    }

    // 비밀번호 재설정
    @PatchMapping("/modify/pw")
    public ResponseEntity resetPw(@RequestBody ResetPwRequest resetPwRequest){
        userService.resetPw(resetPwRequest);

        return new ResponseEntity<String>("비밀번호 변경 완료", HttpStatus.OK);
    }
}

