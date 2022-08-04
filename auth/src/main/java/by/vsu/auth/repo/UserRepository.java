package by.vsu.auth.repo;

import by.vsu.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User repository.
 *
 * Date:24/07/2022
 *
 * @author Dzianis Zatsiupa
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Return user by username.
     *
     * @param username
     * @return User
     */
    User findUserByUsername(String username);

}
