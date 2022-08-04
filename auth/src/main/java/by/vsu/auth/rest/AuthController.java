package by.vsu.auth.rest;

import by.vsu.auth.dto.UserDto;
import by.vsu.auth.model.Role;
import by.vsu.auth.model.User;
import by.vsu.auth.service.UserService;
import by.vsu.auth.token.TokenProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;


/**
 * The token provider class contains util methods for token processing and generation.
 *
 * Date:24/07/2022
 *
 * @author Dzianis Zatsiupa
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    /**
     * User service.
     */
    private final UserService userService;
    /**
     * Token provider.
     */
    private final TokenProvider tokenProvider;

    /**
     * Verify user refresh token and send new access token.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = tokenProvider.getAlgorithm();
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.findByUsername(username);
                String accessToken = tokenProvider.createAccessToken(user.getUsername(), user.getRoles());
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getStackTrace().toString());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                exception.printStackTrace();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                try {
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                } catch (IOException e) {
                    throw new RuntimeException("Object mapping exception");
                }
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    /**
     * Return all users.
     * @return List<User>
     */
    @GetMapping("/api/user/all")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAll();
        return  new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Add new user.
     * @param userDto
     * @return user
     */
    @PostMapping("/api/user/add")
    public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {
        User user = new User();

        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());

        List<Role> roles = userDto.getRoles().stream()
                .map(r -> {
                    Role role = new Role();
                    role.setName(r);
                    return role;
                })
                .toList();

        user = userService.register(user);
        user.setRoles(roles);
        user = userService.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
