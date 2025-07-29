package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/{username}")
    public String getUser(@PathVariable String username) {
        // ❌ SQL Injection vulnerability
        String sql = "SELECT email FROM users WHERE username = '" + username + "'";
        return jdbcTemplate.queryForObject(sql, String.class);
    }
}

