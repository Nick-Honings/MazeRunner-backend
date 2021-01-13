package com.mazerunner.maze.logic.user;

import java.util.UUID;

public interface Player {
    String getId();
    String getName();
    int getCoinsCollected();
    void incrementCoinsCollected();
    int[] getSpawnPosition();
    void setSpawnPosition(int[] spawnPosition);

}
