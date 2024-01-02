package com.greenfox.reddit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
    private String avatar;
    private boolean admin;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Post> posts;

    public User(String name, String password, String avatar) {
        this.name = name;
        this.password = password;
        this.avatar = avatar;
        this.admin = false;
    }
}
