package com.mazerunner.maze.sockets.messages.outbound;

public class MovementUpdate {

    private final String playerId;
    private final float x;
    private final float y;
    private final float rotation;

    public MovementUpdate(String playerId, float x, float y, float rotation) {
        this.playerId = playerId;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public String getPlayerId() {
        return playerId;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }   

    public float getRotation() {
        return rotation;
    }
}
