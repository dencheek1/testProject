package by.vsu.auth.config;

import by.vsu.auth.filter.CustomAuthenticationFilter;
import by.vsu.auth.filter.CustomAuthorizationFilter;
import by.vsu.auth.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * The application web security configuration class.
 *
 * Date:24/07/2022
 *
 * @author Dzianis Zatsiupa
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {
    /**
     * User details service.
     */
    private final UserDetailsService userDetailsService;

    /**
     * Token provider.
     */
    private final TokenProvider tokenProvider;


    /**
     * Create application web security filter chain bean.
     *
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter
                = new CustomAuthenticationFilter(tokenProvider, userDetailsService);

        customAuthenticationFilter.setFilterProcessesUrl("/api/login");

        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(STATELESS);

        http.authorizeRequests()
                .antMatchers("/api/login/**", "/token/refresh/**")
                .permitAll();

        http.authorizeRequests()
                .antMatchers(GET, "/api/user/**")
                .hasAnyAuthority("ROLE_ADMIN");

        http.authorizeRequests()
                .antMatchers(POST, "/api/**")
                .hasAnyAuthority("ROLE_ADMIN");

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(tokenProvider),
                UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
