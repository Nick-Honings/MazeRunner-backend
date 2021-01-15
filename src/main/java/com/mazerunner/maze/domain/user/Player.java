package com.mazerunner.maze.domain.user;

public interface Player {
    String getId();
    String getName();
    int getCoinsCollected();
    void incrementCoinsCollected();
    int[] getSpawnPosition();
    void setSpawnPosition(int[] spawnPosition);

}
