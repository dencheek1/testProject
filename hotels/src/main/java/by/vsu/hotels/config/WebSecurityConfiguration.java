package by.vsu.hotels.config;

import by.vsu.hotels.token.TokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class WebSecurityConfiguration{
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenFilter filter;
//    private final TokenProvider tokenProvider;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(userDetailsService, tokenProvider);
//        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.httpBasic().disable();
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(STATELESS).and();
//        http.authorizeRequests().antMatchers("/swagger-ui.html").permitAll();
//        http.authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests().antMatchers("/api/login/**", "/token/refresh/**").permitAll();
//        http.authorizeRequests().antMatchers(GET, "/api/user/**").hasAnyAuthority("ROLE_USER");
//        http.authorizeRequests().antMatchers(POST, "/api/user/save/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().anyRequest().permitAll();
        return http.build();
    }

}
