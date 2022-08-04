package by.vsu.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The application configuration class.
 *
 * Date:24/07/2022
 *
 * @author Dzianis Zatsiupa
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    /**
     * Create password encoder bean.
     *
     * @return PasswordEncoder bean.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

}
