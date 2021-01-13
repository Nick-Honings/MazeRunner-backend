package com.mazerunner.maze.sockets.broker;

public interface IAction<T> {
    void invoke(T args);
}
