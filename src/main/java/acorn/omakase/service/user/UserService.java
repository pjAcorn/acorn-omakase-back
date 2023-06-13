package acorn.omakase.service.user;

import acorn.omakase.domain.user.User;
import acorn.omakase.dto.userdto.*;
import acorn.omakase.repository.UserMapper;
import acorn.omakase.token.TokenProvider;
import acorn.omakase.token.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisUtil redisUtil;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;
    private final TokenProvider tokenProvider;


    public void signup(SignupRequest signupRequest) {

        User user = User.of(signupRequest);

        user.encodingPassword(encoder.encode(user.getPassword()));
        userMapper.signup(user);
    }

    // 아이디 찾기
    public String findId(FindIdRequest findIdRequest) {

        String email = findIdRequest.getEmail();
        String redisEmail = redisUtil.getData(findIdRequest.getCode());

        if (!email.equals(redisEmail)){
            throw new IllegalStateException("인증번호 불일치");
        }

        return Optional.ofNullable(userMapper.findId(findIdRequest))
                .orElseThrow(() -> new IllegalStateException("가입된 정보가 없습니다"));
    }

    // 비밀번호 찾기
    public void findPw(FindPwRequest findPwRequest){
        String email = findPwRequest.getEmail();
        String redisEmail = redisUtil.getData(findPwRequest.getCode());

        if(!email.equals(redisEmail)){
            throw new IllegalStateException("인증번호 불일치");
        }

        int pwChk = userMapper.findPw(findPwRequest);

        if(!(pwChk>0)){
            throw new IllegalStateException("가입된 정보가 없습니다.");
        }
    }


    public LoginResponse login(LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getLoginId(), loginRequest.getPassword());

        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        User user = findById(Long.valueOf(authenticate.getName()));

        TokenResponse tokenResponse = tokenProvider.generateTokenDto(authenticate);

        redisUtil.setDataExpire(authenticate.getName(), tokenResponse.getRefreshToken(), 1000 * 60 * 60 * 24 * 7);

        LoginResponse loginResponse = new LoginResponse(user.getUserId(), user.getNickname(), tokenResponse);

        return loginResponse;
    }

    public User findById(Long userId){
        return userMapper.findById(userId);
    }

    // 로그아웃
    public void logout(String accessToken, String refreshToken) throws Exception {
        // 1. 검증
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new Exception("인가 실패");
        }

        // 2. Access Token 에서 User ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        String userId = authentication.getName();

        // 3. Redis 에서 해당 ID 로 저장된 Refresh Token 이 있는지 여부 확인 후 삭제
        String findRefreshToken = redisUtil.getData(userId);
        if (findRefreshToken != null) {
            redisUtil.deleteData(userId);
        }
        // 남은 유효시간
        Long expiration = tokenProvider.getExpiration(accessToken);

        // 4. 해당 Access Token 저장
        redisUtil.setDataExpire(accessToken, "logout", expiration);
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
    public void idChk(IdChkRequest idChkRequest) {

        int check = userMapper.idChk(idChkRequest);

        if (check > 0) {
            throw new IllegalStateException("중복된 아이디 입니다.");
        }
    }

    // 비밀번호 변경
    public void resetPw(ResetPwRequest resetPwRequest){
        String pw1 = resetPwRequest.getPw1();
        String pw2 = resetPwRequest.getPw2();

        if(!pw1.equals(pw2)){
            throw new IllegalStateException("두 비밀번호가 일치하지 않습니다.");
        }

        resetPwRequest.encodingPassword(encoder.encode(resetPwRequest.getPw1()));
        userMapper.resetPw(resetPwRequest);
    }
}