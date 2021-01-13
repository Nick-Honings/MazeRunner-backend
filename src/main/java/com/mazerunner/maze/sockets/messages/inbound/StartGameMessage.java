package com.mazerunner.maze.sockets.messages.inbound;

public class StartGameMessage {

    private final int rows;
    private final int columns;

    public StartGameMessage(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
