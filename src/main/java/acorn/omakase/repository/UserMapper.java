package acorn.omakase.repository;

import acorn.omakase.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getUserList();

    void signup(User user);

    String findid(User findid);
}