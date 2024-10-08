package com.komanrudden.multiauthapi.model.repository;

import com.komanrudden.multiauthapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
