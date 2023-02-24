package com.marcinbielenit.auth.model;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author marcin
 */
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.email = ?1")
    public Optional<User> findByEmail(String email);
    @Query("select case when count(u)> 0 then true else false end from User u where u.email = ?1")
    public Boolean existsUserByEmail(String email);
    public User save(User user);
}
