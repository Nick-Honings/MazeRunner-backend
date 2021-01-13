package com.mazerunner.maze.sockets.messages.outbound;

import java.util.List;

public class MovementUpdate {

    private List<String> content;

    public MovementUpdate() {
    }

    public MovementUpdate(List<String> content) {
        this.content = content;
    }

    public List<String> getContent() {
        return content;
    }

}
