package com.mazerunner.maze.domain.lobby;

import java.util.ArrayList;
import java.util.List;

public class LobbyManager {

    private final List<Lobby> lobbies;

    public LobbyManager(){
        this.lobbies = new ArrayList<>();
    }

    // Todo: add return values to these methods
    public boolean addLobby(Lobby lobby){
        // Todo: check if not the same lobbyid, otherwise replace it

        if(lobby != null){
            this.lobbies.add(lobby);
            return true;
        }
        return false;
    }

    public boolean removeLobby(String id){
        if(id != null){
            int count = this.lobbies.size();
            this.lobbies.removeIf(u -> u.getId().equals(id));
            return count > this.lobbies.size();
        }
        return false;
    }

    public Lobby getById(String id){
        return this.lobbies.stream()
                .filter(u -> u.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public Lobby getByLobbyCode(String code){
        Lobby output = null;
        for(Lobby lobby : lobbies){
            if(lobby.getLobbyCode().compareTo(code) == 0){
                System.out.println("Foudn lobby");
                output = lobby;
            }
        }
        return output;
    }

    public List<Lobby> getLobbies(){
        return this.lobbies;
    }

}
