package com.example.springsecurityjwt.repo;

import com.example.springsecurityjwt.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepo extends JpaRepository<Template, Long> {
    long countByName(String name);
}
