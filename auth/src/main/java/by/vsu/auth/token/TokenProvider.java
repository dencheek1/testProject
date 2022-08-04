package by.vsu.auth.token;

import by.vsu.auth.model.Role;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

/**
 * The token provider class contains util methods for token processing and generation.
 *
 * Date:24/07/2022
 *
 * @author Dzianis Zatsiupa
 */
@Component
public class TokenProvider {

    /**
     * The secret key word.
     */
    @Value("${jwt.token.secret}")
    private String secret;

    /**
     * Token issuer.
     */
    @Value("${jwt.token.issuer}")
    private String issuer;

    /**
     * Access token expiration time.
     */
    @Value("${jwt.token.expired.access}")
    private long validityInMillisecondsAccessToken;

    /**
     * Refresh token expiration time.
     */
    @Value("${jwt.token.expired.refresh}")
    private long validityInMillisecondsRefreshToken;

    /**
     * Use details service.
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Method initialise token provider.
     * Encode the secret word with base 64 encoder.
     */
    @PostConstruct
    void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    /**
     * Create access token based on username and user authorities.
     *
     * @param username
     * @param authorities
     * @return access token
     */
    public String createAccessToken(String username, Collection<? extends GrantedAuthority> authorities) {
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + validityInMillisecondsAccessToken))
                .withIssuer(issuer)
                .withClaim("roles", authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(getAlgorithm());
        return token;
    }

    /**
     * Create access token based on username and user authorities.
     * User authorities generated from list of user roles.
     *
     * @param username
     * @param roles
     * @return access token
     */
    public String createAccessToken(String username, List<Role> roles) {
        return createAccessToken(username, mapToGrantedAuthorities(roles));
    }

    /**
     * Generate refresh token from username.
     * @param username
     * @return refresh token
     */
    public String createRefreshToken(String username) {
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + validityInMillisecondsRefreshToken))
                .withIssuer(issuer)
                .sign(getAlgorithm());
        return token;
    }

    /**
     * Map user role list to list of GrantedAuthority.
     * @param roles
     * @return List<GrantedAuthority>
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Method will return user authentication based on token.
     * @param token
     * @return authentication
     * @throws Exception
     */
    public Authentication getAuthentication(String token) throws Exception {
        Algorithm algorithm = getAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
        Authentication authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
        return authenticationToken;
    }

    /**
     * Return token provider algorithm.
     * @return Algorithm.
     */
    public Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret.getBytes());
    }



}
