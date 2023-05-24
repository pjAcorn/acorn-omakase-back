package acorn.omakase.repository;

import acorn.omakase.domain.User;
import acorn.omakase.dto.userdto.LoginRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getUserList();

    void signup(User user);

    User login(LoginRequest loginRequest);

    String findid(User findid);
}