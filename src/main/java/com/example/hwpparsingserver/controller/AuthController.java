package com.example.hwpparsingserver.controller;


import com.example.hwpparsingserver.domain.UserDomain;
import com.example.hwpparsingserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserDomain userDomain) {
        Map<String, Object> response = new HashMap<>();

        boolean isAuthenticated = userService.authUser(userDomain);
        System.out.println("!!");
        if (isAuthenticated) {
            response.put("status", "success");
            response.put("message", "Authentication successful");
        } else {
            response.put("status", "failure");
            response.put("message", "Authentication failed");
        }

        return ResponseEntity.ok(response);
    }

}
