package com.mazerunner.maze.logic.lobby;

import com.mazerunner.maze.logic.user.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Lobby {

    private final UUID id;
    private final UUID lobbyCode;
    private final List<Player> players;

    public Lobby(){
        this.id = UUID.randomUUID();
        this.lobbyCode = UUID.randomUUID();
        this.players = new ArrayList<>();
    }

    // Todo: add return values to these methods
    public boolean addPlayer(Player player){
        if(player != null){
            this.players.add(player);
            return true;
        }
        return false;
    }

    public boolean removePlayer(UUID id){
        if(id != null){
            return this.players.removeIf(p -> p.getId() == id);
        }
        return false;
    }

    public Player getPlayer(UUID id){
        return this.players.stream()
                .filter(u -> u.getId() == id)
                .findAny()
                .orElse(null);
    }

    public UUID getId() {
        return id;
    }

    public UUID getLobbyCode() {
        return lobbyCode;
    }
}
