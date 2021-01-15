package com.mazerunner.maze.sockets.messages.outbound;

import com.mazerunner.maze.domain.maze.Maze;

public class GameSetupMessage {

    private final String mazeString;
    private final int rows;
    private final int columns;
    private final int coins;

    public GameSetupMessage(Maze maze) {
        this.mazeString = maze.getMazeString();
        this.rows = maze.getRows();
        this.columns = maze.getColumns();
        this.coins = maze.getTotalCoins();
    }

    public String getMazeString() {
        return mazeString;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getCoins() {
        return coins;
    }
}
