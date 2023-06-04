package acorn.omakase.controller;

import acorn.omakase.domain.User;
import acorn.omakase.dto.userdto.*;
import acorn.omakase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignupRequest signupRequest) {
        userService.signup(signupRequest);

        return new ResponseEntity(HttpStatus.OK);
    }

    // 아이디 찾기
    @PostMapping("/find/id")
    public ResponseEntity findId(@RequestBody FindIdRequest findIdRequest){
            String id = userService.findId(findIdRequest);

            return new ResponseEntity(id, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) throws Exception {
        User userId = userService.login(loginRequest);

        return new ResponseEntity(userId, HttpStatus.OK);
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
        System.out.println("아이디 사용 가능");
        return new ResponseEntity(HttpStatus.OK);
    }

}

