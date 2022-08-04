package by.vsu.flight.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class TokenUtil {

    @Value("${jwt.token.secret}")
    private String secret;

    private JWTVerifier verifier;

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION);
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

    @PostConstruct
    public void init(){
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        verifier = JWT.require(algorithm).build();
    }

    public Collection<SimpleGrantedAuthority> getAuthorities(String token)throws Exception{
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        authorities = Arrays.stream(roles).map(a -> new SimpleGrantedAuthority(a)).collect(Collectors.toList());
        return authorities;

    }

    public String getSubject(String token)throws Exception{
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    public Authentication getAuthentication(String token) {
        return new UsernamePasswordAuthenticationToken(token,token);
    }
}
