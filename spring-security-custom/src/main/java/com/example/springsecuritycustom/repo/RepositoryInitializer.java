package com.example.springsecuritycustom.repo;

import com.example.springsecuritycustom.enums.Role;
import com.example.springsecuritycustom.model.Authority;
import com.example.springsecuritycustom.model.Template;
import com.example.springsecuritycustom.model.User;
import org.springframework.beans.factory.SmartInitializingSingleton;
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
        user1.addAuthority(new Authority(UUID.randomUUID(), Role.ROLE_ADMIN, user1));
        user1.addAuthority(new Authority(UUID.randomUUID(), Role.ROLE_MODERATOR, user1));
        userRepo.save(user1);

        User user2 = new User(UUID.randomUUID(), "moderator", passwordEncoder.encode("pass"));
        user2.addAuthority(new Authority(UUID.randomUUID(), Role.ROLE_MODERATOR, user2));
        userRepo.save(user2);
    }
}
