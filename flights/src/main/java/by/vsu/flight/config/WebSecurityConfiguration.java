package by.vsu.flight.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Web security configuration based on a java class configuration.
 * Date:22/07/2022
 *
 * @author Dzianis Zatsiupa
 */
@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class WebSecurityConfiguration {

    /**
     *
     * @param http
     * @return filterChainObject
     * @throws Exception
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
//        CustomAuthenticationFilter customAuthenticationFilter =
//        new CustomAuthenticationFilter(userDetailsService, tokenProvider);
//        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.httpBasic().disable();
        http.authorizeRequests().anyRequest().permitAll();
        http.sessionManagement().sessionCreationPolicy(STATELESS).and();
//        http.authorizeRequests()
//        .antMatchers("/swagger-ui.html").permitAll();
//        http.authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests()
//        .antMatchers("/api/login/**", "/token/refresh/**").permitAll();
//        http.authorizeRequests()
//        .antMatchers(GET, "/api/user/**").hasAnyAuthority("ROLE_USER");
//        http.authorizeRequests()
//        .antMatchers(POST, "/api/user/save/**").hasAnyAuthority("ROLE_ADMIN");
//        http.addFilter(customAuthenticationFilter);
//        http.addFilterBefore(new CustomAuthorizationFilter(),
//        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
