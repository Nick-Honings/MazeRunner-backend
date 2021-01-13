package com.mazerunner.maze.sockets.broker;

public class Action<T> implements IAction<T> {

    private final IAction<T> action;

    public Action(IAction<T> action){
        this.action = action;
    }


    @Override
    public void invoke(T args) {
        this.action.invoke(args);
    }
}
