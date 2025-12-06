package com.example.alfresco.dto;

import jakarta.validation.constraints.Pattern;

/**
 * DTO used for updating authenticationMethod and managerName.
 */
public class UserUpdateRequest {

    // null = do not change
    @Pattern(
        regexp = "Password|OTP|MFA",
        message = "authenticationMethod must be one of: Password, OTP, MFA"
    )
    private String authenticationMethod;

    // may be null or empty = clear manager
    private String managerName;

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
