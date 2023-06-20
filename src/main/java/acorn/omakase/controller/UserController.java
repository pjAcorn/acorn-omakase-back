package acorn.omakase.controller;

import acorn.omakase.common.code.SuccessCode;
import acorn.omakase.common.response.ApiResponse;
import acorn.omakase.dto.userdto.*;
import acorn.omakase.service.user.EmailService;
import acorn.omakase.service.user.UserService;
import acorn.omakase.token.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignupRequest signupRequest) {
        userService.signup(signupRequest);

        return new ResponseEntity(new ApiResponse(SuccessCode.SIGNUP_SUCCESS), HttpStatus.OK);
    }


    @PostMapping("/find/id")
    public ResponseEntity findId(@RequestBody FindIdRequest findIdRequest){
        String loginId = userService.findId(findIdRequest);
        log.info("loginId={}", loginId);
        return new ResponseEntity(loginId, HttpStatus.OK);
    }

    // 비밀번호 찾기
    @PostMapping("/find/pw")
    public ResponseEntity findPw(@RequestBody FindPwRequest findPwRequest){
        userService.findPw(findPwRequest);

        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) throws Exception {

        LoginResponse loginResponse = userService.login(loginRequest);

        return new ResponseEntity(loginResponse, HttpStatus.OK);
    }

    // 회원탈퇴
    @PostMapping("/delete")
    public ResponseEntity delete(@RequestBody DeleteIdRequest deleteIdRequest){
        userService.delete(deleteIdRequest);
        return new ResponseEntity(new ApiResponse(SuccessCode.DELETE_USER),HttpStatus.OK);
    }

    // 아이디 중복 확인
    @PostMapping("/signup/id")
    public ResponseEntity duplicationId(@RequestBody IdChkRequest idChkRequest){
        userService.idChk(idChkRequest);

        return new ResponseEntity(new ApiResponse(SuccessCode.CAN_USE_ID),HttpStatus.OK);
    }

    // 이메일 중복 확인
    @PostMapping("/signup/email")
    public ResponseEntity emailChk(EmailChkRequest emailChkRequest){
        userService.emailChk(emailChkRequest);
        return new ResponseEntity(new ApiResponse(SuccessCode.CAN_USE_EMAIL), HttpStatus.OK);
    }

    // 이메일 인증
    @PostMapping("/email")
    public ResponseEntity mailConfirm(@RequestBody EmailAuthRequestDto emailDto) throws MessagingException, UnsupportedEncodingException {

        emailService.sendEmail(emailDto.getEmail());

        // 인증코드를 그대로 반환하는거?
        return new ResponseEntity(HttpStatus.OK);
    }

    // 비밀번호 재설정
    @PatchMapping("/modify/pw")
    public ResponseEntity resetPw(@RequestBody ResetPwRequest resetPwRequest){
        userService.resetPw(resetPwRequest);

        return new ResponseEntity(new ApiResponse(SuccessCode.UPDATE_PASSWORD), HttpStatus.OK);
    }

    // 로그아웃
    @GetMapping("/logout")
    public ResponseEntity logout(
            @RequestHeader(value = "Authorization") String acTokenRequest,
            @RequestHeader(value = "RefreshToken") String rfTokenRequest
    ) {
        String accessToken = acTokenRequest.substring(7);
        String refreshToken = rfTokenRequest.substring(7);
        userService.logout(accessToken, refreshToken);

        return new ResponseEntity(new ApiResponse(SuccessCode.LOGOUT), HttpStatus.OK);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenResponse> reissue(
            @RequestHeader(value = "Authorization") String acTokenRequest,
            @RequestHeader(value = "RefreshToken") String rfTokenRequest) {

        String accessToken = acTokenRequest.substring(7);
        String refreshToken = rfTokenRequest.substring(7);

        TokenResponse tokenResponse = userService.reissue(accessToken, refreshToken);

        return new ResponseEntity(tokenResponse, HttpStatus.OK);
    }



    // 마이페이지
    @GetMapping("/{userId}")
    public ResponseEntity myPage(@PathVariable("userId") Long userId){
        MyPageResponse myPage = userService.myPage(userId);
        return new ResponseEntity(myPage, HttpStatus.OK);
    }
}


