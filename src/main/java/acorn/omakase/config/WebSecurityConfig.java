package acorn.omakase.config;

import acorn.omakase.service.user.RedisUtil;
import acorn.omakase.token.TokenProvider;
import acorn.omakase.token.accessRestriction.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final RedisUtil redisUtil;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable() // ui에서 들어오는 것. auth 기반의 로그인창이 안뜨도록 설정.(security 사용하면 기본 로그인 창이있음)
                .csrf().disable() // crosssite 기능. csrf 보안 기능이 rest api 에서 안쓰이므로 disable.
                .cors()// crosssite 다른 domain 허용. webconfig에서 설정.
                .and()
                .headers().frameOptions().sameOrigin()
                .and()// exception handling 할 때 우리가 만든 클래스를 추가

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 사용할 경우 세션을 사용하지 않는다.
                .and()
                // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
                .apply(new JwtSecurityConfig(tokenProvider, redisUtil))
                .and()
                .formLogin().disable()  //폼로그인 안쓰겠다
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/", "/**", "/users/**").permitAll()
                .anyRequest().permitAll()   // 나머지 API 는 전부 인증 필요
                .and()
                .build();
    }
}
