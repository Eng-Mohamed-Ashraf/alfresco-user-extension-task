package com.example.alfresco.service;

import com.example.alfresco.dto.UserUpdateRequest;
import com.example.alfresco.model.User;
import com.example.alfresco.repository.InMemoryUserRepository;
import org.springframework.stereotype.Service;

/**
 * Business logic: default values, validation, admin-only update.
 */
@Service
public class UserService {

    private final InMemoryUserRepository repository;

    public UserService(InMemoryUserRepository repository) {
        this.repository = repository;
    }

    public User getUser(String username) {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + username);
        }
        return user;
    }

    public User updateUser(String username, UserUpdateRequest request, boolean isAdmin) {
        if (!isAdmin) {
            throw new SecurityException("Only admin can update these fields");
        }

        User user = getUser(username);

        // authenticationMethod
        if (request.getAuthenticationMethod() != null) {
            user.setAuthenticationMethod(request.getAuthenticationMethod());
        } else if (user.getAuthenticationMethod() == null) {
            // default value if missing
            user.setAuthenticationMethod("Password");
        }

        // managerName
        if (request.getManagerName() != null) {
            String managerName = request.getManagerName().trim();

            if (managerName.isEmpty()) {
                // allow clearing manager
                user.setManagerName(null);
            } else {
                if (!repository.existsByUsername(managerName)) {
                    throw new IllegalArgumentException("Manager does not exist");
                }
                user.setManagerName(managerName);
            }
        }

        return repository.save(user);
    }
}
