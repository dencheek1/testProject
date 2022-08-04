package by.vsu.flight.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Application java class configuration.
 * Date:22/07/2022
 *
 * @author Dzianis Zatsiupa
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {


    /**
     * Create BCryptPasswordEncoder bean.
     *
     * @return BCryptPasswordEncoder bean.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder
                = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

}
