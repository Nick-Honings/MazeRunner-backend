package com.mazerunner.maze.sockets.messages.inbound;

import com.mazerunner.maze.sockets.messages.IMessage;

import java.util.UUID;

public class JoinLobbyMessage implements IMessage {

    private final String lobbyCode;
    private final String playerId;
    private final String playerName;


    public JoinLobbyMessage(String lobbyCode, String playerId, String playerName) {
        this.lobbyCode = lobbyCode;
        this.playerId = playerId;
        this.playerName = playerName;
    }

    public String getLobbyCode() {
        return lobbyCode;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }
}
