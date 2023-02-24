package com.marcinbielenit.auth.logic;

import com.marcinbielenit.auth.model.UserSecurity;
import com.marcinbielenit.auth.model.UserRepository;
import com.marcinbielenit.auth.model.User;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author marcin
 */
@Service
public class UserService implements UserDetailsService {
    
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(username);
        if (user.isPresent())
            return new UserSecurity(user.get());
        else
            throw new UsernameNotFoundException("User details not found for the user : " + username);
    }
    
    public void addNewUser(User newUser) {
        if (repository.existsUserByEmail(newUser.getEmail()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exists!");
        newUser.setEnable(false);
        newUser.setAdmin(false);
        repository.save(newUser);
    }
}
