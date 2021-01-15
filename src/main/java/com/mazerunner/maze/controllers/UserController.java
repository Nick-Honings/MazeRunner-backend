package com.mazerunner.maze.controllers;


import com.mazerunner.maze.domain.user.User;
import com.mazerunner.maze.security.JwtUtil;
import com.mazerunner.maze.services.UserService;
import com.mazerunner.maze.validation.Result;
import com.mazerunner.maze.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserValidator validator;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(AuthenticationManager manager, UserService userService, UserValidator validator, @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, JwtUtil jwtUtil){
        this.authenticationManager = manager;
        this.userService = userService;
        this.validator = validator;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/user/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable("name") String name){
        Optional<User> user = userService.findByName(name);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(404).body("User not found");
    }

    @PostMapping("/user")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        Result result = validator.validate(user);
        if (result.isSuccess()) {
            userService.registerUser(user);
            return ResponseEntity.ok("Successfully registered user");
        }

        return ResponseEntity.status(422).body(result.getMessage());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
        }
        catch (BadCredentialsException e) {
            return ResponseEntity.status(403).body("Invalid username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getName());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }
}
