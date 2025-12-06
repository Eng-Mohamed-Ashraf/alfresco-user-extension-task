package com.example.alfresco.repository;

import com.example.alfresco.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple in-memory repository to simulate Alfresco users.
 */
@Repository
public class InMemoryUserRepository {

    // username -> User
    private final Map<String, User> users = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        // Seed some users (simulate cm:person nodes)
        users.put("admin", new User("admin", "Password", null));
        users.put("x.admin", new User("x.admin", "Password", null));
        users.put("test.user", new User("test.user", "Password", null));
    }

    public User findByUsername(String username) {
        return users.get(username);
    }

    public User save(User user) {
        users.put(user.getUsername(), user);
        return user;
    }

    public boolean existsByUsername(String username) {
        return users.containsKey(username);
    }

    public Collection<User> findAll() {
        return users.values();
    }
}