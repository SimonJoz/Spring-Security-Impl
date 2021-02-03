package com.example.springsecurity_basic.model;


import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "authorities") // table must be named 'authorities' - default
public class Authority {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String authority;  // property  must be named 'enabled' - default

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username") // JdbcUserDetailsManager requires mapping by username
    private User user;

    public Authority() {
    }

    public Authority(UUID id, String authority, User user) {
        this.id = id;
        this.authority = authority;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
