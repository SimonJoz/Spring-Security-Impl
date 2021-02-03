package com.example.springsecurity_basic.repo;

import com.example.springsecurity_basic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepo extends JpaRepository<User, UUID> {
}
