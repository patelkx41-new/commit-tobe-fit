package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUser(
        @PathVariable
        @Pattern(regexp = "^[a-zA-Z0-9_]{3,30}$", message = "Invalid username format")
        String username
    ) {
        String sql = "SELECT email FROM users WHERE username = ?";

        try {
            String email = jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);
            return ResponseEntity.ok(new UserResponse(username, email));
        } catch (EmptyResultDataAccessException e) {
            logger.warn("User not found: {}", username);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Error fetching user: {}", username, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

