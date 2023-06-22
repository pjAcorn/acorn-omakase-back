package acorn.omakase.repository;


import acorn.omakase.domain.user.User;
import acorn.omakase.dto.userdto.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



}