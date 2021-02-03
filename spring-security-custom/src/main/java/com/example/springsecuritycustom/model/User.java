package com.example.springsecuritycustom.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    private UUID id;

    @Column(unique = true)
    private String username;
    private String password;

    private Boolean isEnabled = true;
    private Boolean isNotLocked = true;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Authority> userAuthorities = new ArrayList<>();

    public User(User user) {
        this.id = user.id;
        this.isEnabled = user.isEnabled;
        this.isNotLocked = user.isNotLocked;
        this.password = user.password;
        this.username = user.username;
        this.userAuthorities = new ArrayList<>(user.userAuthorities);
    }

    public User(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public void addAuthority(Authority authority) {
        this.userAuthorities.add(authority);
    }
}
