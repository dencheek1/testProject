package by.vsu.auth.filter;

import by.vsu.auth.token.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Authentication filter class. Authenticate a user by name and password.
 * If authentication was successful sends to user an access token and a refresh token.
 *
 * Date:24/07/2022
 *
 * @author Dzianis Zatsiupa
 */
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationFilter
        extends UsernamePasswordAuthenticationFilter {
    /**
     * Token provider provide the token and all needed functions.
     */
    private final TokenProvider tokenProvider;
    /**
     * Spring security user details service.
     */
    private final UserDetailsService userService;

    /**
     * Prepare authentication.
     *
     * @param request from which to extract parameters and perform the authentication
     * @param response the response, which may be needed if the implementation has to do a
     * redirect as part of a multi-stage authentication process (such as OpenID).
     * @return Authentication.
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        return authenticationToken;
    }

    /**
     * Sends to user an access and refresh tokens.
     *
     * @param request
     * @param response
     * @param chain
     * @param authentication the object returned from the <tt>attemptAuthentication</tt>
     * method.
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication)
            throws IOException, ServletException {

        String username = (String) authentication.getPrincipal();

        UserDetails user = userService.loadUserByUsername(username);

        String accessToken = tokenProvider.createAccessToken(
                user.getUsername(),
                user.getAuthorities()
        );

        String refreshToken =
                tokenProvider.createRefreshToken(user.getUsername());
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
