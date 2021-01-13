package com.mazerunner.maze.sockets.messages.inbound;

public class LeaveLobbyMessage {

    private String lobbyCode;
    private String playerId;

    public String getLobbyCode() {
        return lobbyCode;
    }

    public void setLobbyCode(String lobbyCode) {
        this.lobbyCode = lobbyCode;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
