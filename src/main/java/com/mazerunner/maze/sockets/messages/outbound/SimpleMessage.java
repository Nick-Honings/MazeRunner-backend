package com.mazerunner.maze.sockets.messages.outbound;

public class SimpleMessage {

    private final String content;

    public SimpleMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
