package com.example.alfresco.model;

/**
 * Simple user model simulating Alfresco cm:person extension.
 */
public class User {

    private String username;
    private String authenticationMethod; // Password / OTP / MFA
    private String managerName;          // must be existing username or null

    public User() {
    }

    public User(String username, String authenticationMethod, String managerName) {
        this.username = username;
        this.authenticationMethod = authenticationMethod;
        this.managerName = managerName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthenticationMethod() {
        return authenticationMethod;
    }

    public void setAuthenticationMethod(String authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
