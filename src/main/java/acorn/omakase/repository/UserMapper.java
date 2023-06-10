package acorn.omakase.repository;

import acorn.omakase.domain.User;

import acorn.omakase.dto.userdto.*;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getUserList();

    void signup(User user);


    String findId(FindIdRequest findId);

    int findPw(FindPwRequest findPw);

    int login(LoginRequest loginRequest);

    // 회원 탈퇴
    int deleteId(Long userId);

    // 비밀번호 가져오기(회원탈퇴)
    String getPw(Long userId);

    // 아이디 중복 검사
    int idValidate(User user);

    // 비밀번호 변경
    int resetPw(ResetPwRequest resetPwRequest);

    // 회원정보 가져오기
    User findById(Long userId);
}