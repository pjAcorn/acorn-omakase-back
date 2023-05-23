package acorn.omakase.controller;

import acorn.omakase.domain.User;
import acorn.omakase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/userlist")
    public ResponseEntity getUserList() {
        List<User> userList = userService.getUserList();
        System.out.println("test입니다");
        return new ResponseEntity(userList, HttpStatus.OK);
    }

}