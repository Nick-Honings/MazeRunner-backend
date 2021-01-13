package com.mazerunner.maze.sockets.messages.inbound;

public class CreateLobbyMessage {

    private final String playerId;
    private final String name;

    public CreateLobbyMessage(String playerId, String name){
        this.playerId = playerId;
        this.name = name;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }
}
