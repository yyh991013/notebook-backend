package com.notebook.notebookbackend.controller;

import com.notebook.notebookbackend.dto.LoginDTO;
import com.notebook.notebookbackend.dto.RegisterDTO;
import com.notebook.notebookbackend.dto.UpdatePasswordDTO;
import com.notebook.notebookbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginData) {
        String token = userService.login(loginData);
        if (token != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(token);
        }

        return ResponseEntity.ok("Not Found");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerData) {
        boolean result = userService.register(registerData);
        if (!result) {
            return ResponseEntity.status(HttpStatus.FOUND).body("Occupied");
        }
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordData,
                                                 @RequestHeader HttpHeaders headers) {
        if (headers.containsKey("token")) {

        }
    }
}
