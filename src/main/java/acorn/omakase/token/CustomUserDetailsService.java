package acorn.omakase.token;

import acorn.omakase.domain.User;
import acorn.omakase.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        System.out.println("loginId : " + loginId);
        return userMapper.findByLoginId(loginId)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(loginId + "를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(User user) {
        return new PrincipalDetail(user);
    }
}
