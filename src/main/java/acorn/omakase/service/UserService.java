package acorn.omakase.service;

import acorn.omakase.domain.User;
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
        return userMapper.getUserList();
    }

}