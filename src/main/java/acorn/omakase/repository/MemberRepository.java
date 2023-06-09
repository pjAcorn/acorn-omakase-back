package acorn.omakase.repository;


import acorn.omakase.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<User, String> {
    Optional<User> findByLoginId(String username);
}
