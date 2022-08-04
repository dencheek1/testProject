package by.vsu.auth.service;

import by.vsu.auth.model.User;

import java.util.List;

/**
 * User service interface.
 *
 * Date:24/07/2022
 *
 * @author Dzianis Zatsiupa
 */
public interface UserService {

    /**
     * Register user.
     *
     * @param user
     * @return registered user.
     */
    User register(User user);

    /**
     * Return all available users.
     * @return list of users.
     */
    List<User> getAll();

    /**
     * Find a user by username.
     *
     * @param username
     * @return user.
     */
    User findByUsername(String username);

    /**
     * Find a user by id.
     *
     * @param id
     * @return user.
     */
    User findById(Long id);

    /**
     * Attempts to delete a user by user ID.
     * @param id
     */
    void delete(Long id);

    /**
     * Update user information.
     * @param user
     * @return user
     */
    User update(User user);
}
