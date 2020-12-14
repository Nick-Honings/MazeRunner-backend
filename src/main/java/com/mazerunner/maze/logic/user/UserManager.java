package com.mazerunner.maze.logic.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserManager {

    private List<User> users;

    public UserManager(){
        this.users = new ArrayList<>();

    }
    // Todo: add return values to these methods
    public void addUser(User user){
        if(user != null){
            this.users.add(user);
        }
    }

    public void removeUser(UUID id){
        if(id != null){
            this.users.removeIf(u -> u.getId() == id);
        }
    }

    public IUser getById(UUID id){
        return this.users.stream()
                .filter(u -> u.getId() == id)
                .findAny()
                .orElse(null);
    }
}
