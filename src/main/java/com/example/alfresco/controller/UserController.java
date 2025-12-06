package com.example.alfresco.controller;

import com.example.alfresco.dto.UserUpdateRequest;
import com.example.alfresco.model.User;
import com.example.alfresco.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST API:
 *  GET /users/{username}
 *  PUT /users/{username}?admin=true
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // In a real system, admin check would come from security.
    // For the interview demo we simulate it with a query param.
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        try {
            User user = userService.getUser(username);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error(ex.getMessage()));
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(
            @PathVariable String username,
            @Valid @RequestBody UserUpdateRequest request,
            @RequestParam(name = "admin", defaultValue = "false") boolean isAdmin) {

        try {
            User updated = userService.updateUser(username, request, isAdmin);
            return ResponseEntity.ok(updated);
        } catch (SecurityException se) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(error(se.getMessage()));
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error(iae.getMessage()));
        }
    }

    private static ErrorResponse error(String message) {
        return new ErrorResponse(message);
    }

    // Simple error DTO
    static class ErrorResponse {
        private String message;

        public ErrorResponse() {
        }

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
