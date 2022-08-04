package by.vsu.auth.service;

import by.vsu.auth.model.Role;
import by.vsu.auth.model.User;
import by.vsu.auth.repo.RoleRepository;
import by.vsu.auth.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User service implementation.
 * Also implements UserDetailsService.
 * <p>
 * Date:24/07/2022
 *
 * @author Dzianis Zatsiupa
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    /**
     * Password encoder.
     */
    @Autowired
    @Lazy
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * User repository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Role repository.
     */
    @Autowired
    private RoleRepository roleRepository;

    /**
     * New User registration method.
     *
     * @param user
     * @return registered user.
     */
    @Override
    public User register(User user) {
        User registeredUser = null;

        if (userRepository.findUserByUsername(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of(roleRepository.findByName("ROLE_USER")));
            Date date = new Date();
            user.setUpdated(date);
            user.setCreated(date);
            registeredUser = userRepository.save(user);
        }

        return registeredUser;
    }

    /**
     * Get all available user.
     *
     * @return List of user.
     */
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Find user by username.
     *
     * @param username
     * @return user.
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }


    /**
     * Find use by id.
     *
     * @param id
     * @return User.
     */
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Delete user.
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Load a user details by username.
     *
     * @param username the username identifying the user whose data is required.
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles())
        );
    }

    /**
     * Update user.
     *
     * @param user user who will be updated.
     * @return
     */
    @Override
    public User update(User user) {
        User oldUser = userRepository.findById(user.getId()).orElse(null);
        if (userRepository.existsById(user.getId())) {
            user.setCreated(oldUser.getCreated());
            List<Role> roles = user.getRoles().stream().map(r -> roleRepository.findByName(r.getName())).toList();
            if (roles.contains(null)) {
                throw new RuntimeException("User role mapping exception");
            }
            user.setRoles(roles);
            user.setUpdated(new Date());
            return userRepository.save(user);
        }
        return null;
    }

    /**
     * Map the list of roles to list of granted authorities.
     *
     * @param roles
     * @return list of granted authorities.
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
