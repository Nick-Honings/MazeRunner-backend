package com.mazerunner.maze.logic.user;

import java.util.UUID;

public interface Player {
    UUID getId();
    String getName();
    int getCoinsCollected();
    void incrementCoinsCollected();

}
