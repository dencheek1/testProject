package by.vsu.auth.repo;

import by.vsu.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Role repository interface.
 *
 * Date:24/07/2022
 *
 * @author Dzianis Zatsiupa
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Return role by role name.
     * @param name
     * @return Role
     */
    Role findByName(String name);

}
