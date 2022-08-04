package by.vsu.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

/**
 * Spring boot aplication class.
 *
 * Date:06/07/2022
 *
 * @author Dzianis Zatsiupa
 */
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class AuthApplication {

    /**
     * Application main method.
     * @param args
     */
	public static void main(final String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
