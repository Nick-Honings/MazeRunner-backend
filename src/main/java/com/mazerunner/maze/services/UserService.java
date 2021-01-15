package com.mazerunner.maze.services;


import com.mazerunner.maze.domain.user.User;
import com.mazerunner.maze.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    public Optional<User> findByName(String name){
        return repository.findByName(name);
    }

    public Optional<User> findById(String id){
        return repository.findById(id);
    }

    public User updateUser(User user){
        return repository.save(user);
    }

    public void deleteUserById(String id){
        repository.deleteById(id);
    }

    public void deleteUser(User user){
        repository.delete(user);
    }

}
