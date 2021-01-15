package com.mazerunner.maze.sockets.messages.outbound;

public class MovementUpdate {

    private final Integer playerId;
    private final Integer x;
    private final Integer y;
    private final Integer z;
    private final float rotation;

    public MovementUpdate(Integer playerId, Integer x, Integer y, Integer z, float rotation) {
        this.playerId = playerId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.rotation = rotation;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    public float getRotation() {
        return rotation;
    }
}
