package acorn.omakase.service;

import acorn.omakase.domain.Login;
import acorn.omakase.domain.User;
import acorn.omakase.dto.userdto.LoginRequest;
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

        return userMapper.getUserList();
    }

    public void signup(SignupRequest signupRequest) {

        User user = User.of(signupRequest);
        userMapper.signup(user);

    }

    public int login(LoginRequest loginRequest) {
        Login login = Login.of(loginRequest);

        return userMapper.login(login);
    }
}