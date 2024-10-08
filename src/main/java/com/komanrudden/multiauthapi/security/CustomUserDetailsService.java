package com.komanrudden.multiauthapi.security;

import com.komanrudden.multiauthapi.model.entity.User;
import com.komanrudden.multiauthapi.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class for loading user details from the database.
 * <p>
 * This class implements `UserDetailsService` to provide user details for authentication.
 * It uses `@Service` to indicate that it's a service component in the Spring context,
 * and `@RequiredArgsConstructor` for constructor injection.
 * </p>
 *
 * <p>Methods:</p>
 * <ul>
 *   <li><b>loadUserByUsername</b>: Loads user details by email for authentication.</li>
 *   <li><b>loadUserById</b>: Loads user details by user ID.</li>
 * </ul>
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with email: %s.", email)));

        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with ID: %s.", id)));

        return UserPrincipal.create(user);
    }

}
