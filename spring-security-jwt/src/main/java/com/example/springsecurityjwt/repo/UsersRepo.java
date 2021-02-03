package com.example.springsecurityjwt.repo;

import com.example.springsecurityjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepo extends JpaRepository<User, UUID> {

    Optional<User> findAllByUsername(String username);
}
