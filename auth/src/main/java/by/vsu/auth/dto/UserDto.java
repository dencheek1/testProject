package by.vsu.auth.dto;

import lombok.Data;

import java.util.List;

/**
 * User DTO class.
 *
 * Date:24/07/2022
 *
 * @author Dzianis Zatsiupa
 */
@Data
public class UserDto {

    /**
     * Username.
     */
    private String username;

    /**
     * User email address.
     */
    private String email;

    /**
     * User password.
     */
    private String password;

    /**
     * User roles.
     */
    private List<String> roles;
}
