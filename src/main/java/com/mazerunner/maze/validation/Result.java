package com.mazerunner.maze.validation;

public class Result {

    private boolean success;
    private String message;

    public Result(boolean success, String msg) {
        this.success = success;
        this.message = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
