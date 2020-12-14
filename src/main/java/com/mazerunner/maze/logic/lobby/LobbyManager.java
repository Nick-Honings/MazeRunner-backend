package com.mazerunner.maze.logic.lobby;

import com.mazerunner.maze.logic.user.IUser;
import com.mazerunner.maze.logic.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LobbyManager {

    private final List<Lobby> lobbies;

    public LobbyManager(){
        this.lobbies = new ArrayList<>();
    }

    // Todo: add return values to these methods
    public void addLobby(Lobby lobby){
        if(lobby != null){
            this.lobbies.add(lobby);
        }
    }

    public void removeLobby(UUID id){
        if(id != null){
            this.lobbies.removeIf(u -> u.getId() == id);
        }
    }

    public Lobby getById(UUID id){
        return this.lobbies.stream()
                .filter(u -> u.getId() == id)
                .findAny()
                .orElse(null);
    }
}
