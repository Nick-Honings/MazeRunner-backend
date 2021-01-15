package com.mazerunner.maze.sockets.messages.outbound;

import com.mazerunner.maze.domain.lobby.Lobby;
import com.mazerunner.maze.domain.user.Player;

import java.util.List;

public class LobbyMessage {

    private String id;
    private String lobbyCode;
    private List<Player> players;
    private Integer minPlayers;
    private Integer maxPlayers;


    public LobbyMessage(Lobby lobby) {
        this.id = lobby.getId();
        this.lobbyCode = lobby.getLobbyCode();
        this.players = lobby.getPlayers();
        this.minPlayers = lobby.getMinPlayers();
        this.maxPlayers = lobby.getMaxPlayers();
    }

    public LobbyMessage(){}

    public String getId() {
        return id;
    }

    public String getLobbyCode() {
        return lobbyCode;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Integer getMinPlayers() {
        return minPlayers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }
}
