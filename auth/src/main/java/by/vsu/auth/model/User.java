package by.vsu.auth.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

/**
 * User entity class.
 *
 * Date:24/07/2022
 *
 * @author Dzianis Zatsiupa
 */
@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    /**
     * User name.
     */
    @Column(name = "username")
    private String username;

    /**
     * User password.
     */
    @Column(name = "password")
    private String password;

    /**
     * User email address.
     */
    @Column(name = "email")
    private String email;

    /**
     * User roles.
     */
    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "user_roles",
                joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;
}
