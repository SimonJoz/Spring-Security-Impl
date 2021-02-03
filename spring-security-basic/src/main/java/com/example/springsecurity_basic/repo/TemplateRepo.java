package com.example.springsecurity_basic.repo;

import com.example.springsecurity_basic.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepo extends JpaRepository<Template, Long> {
    long countByName(String name);
}
