package com.mazerunner.maze.domain.user;

import java.util.ArrayList;
import java.util.List;

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

    public void removeUser(String id){
        if(id != null){
            this.users.removeIf(u -> u.getId().equals(id));
        }
    }

    public IUser getById(String id){
        return this.users.stream()
                .filter(u -> u.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
