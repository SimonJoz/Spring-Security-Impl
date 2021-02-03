package com.example.springsecurity_basic.repo;

import com.example.springsecurity_basic.model.Authority;
import com.example.springsecurity_basic.model.Template;
import com.example.springsecurity_basic.model.User;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RepositoryInitializer implements SmartInitializingSingleton {

    private final TemplateRepo templateRepo;
    private final UsersRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public RepositoryInitializer(TemplateRepo templateRepo, UsersRepo userRepo,
                                 PasswordEncoder passwordEncoder) {
        this.templateRepo = templateRepo;
        this.userRepo = userRepo;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void afterSingletonsInstantiated() {

        templateRepo.save(new Template("Template1"));
        templateRepo.save(new Template("Template2"));
        templateRepo.save(new Template("Template3"));
        templateRepo.save(new Template("Template4"));

        User user1 = new User(UUID.randomUUID(), "admin", passwordEncoder.encode("pass"));
        user1.addAuthority(new Authority(UUID.randomUUID(), "READ", user1));
        user1.addAuthority(new Authority(UUID.randomUUID(), "WRITE", user1));
        userRepo.save(user1);

        User user2 = new User(UUID.randomUUID(), "moderator", passwordEncoder.encode("pass"));
        user2.addAuthority(new Authority(UUID.randomUUID(), "READ", user2));
        userRepo.save(user2);

    }
}
