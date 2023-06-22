package acorn.omakase.util;

import acorn.omakase.common.code.ErrorCode;
import acorn.omakase.common.exception.CustomIllegalStateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtil {

    private SecurityUtil() {}

    // SecurityContext 에 유저 정보가 저장되는 시점
    // Request 가 들어올 때 JwtFilter 의 doFilter 에서 저장
    public static Long getCurrentUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication : {}",authentication);
        if (authentication == null || authentication.getName() == null) {
            throw new CustomIllegalStateException(ErrorCode.NOT_FOUND_INFORMATION);
        }

        return Long.parseLong(authentication.getName());
    }
}