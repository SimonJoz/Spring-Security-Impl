package com.example.springsecuritycustom.repo;

import com.example.springsecuritycustom.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepo extends JpaRepository<Template, Long> {
    long countByName(String name);
}
