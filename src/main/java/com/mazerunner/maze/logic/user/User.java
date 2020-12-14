package com.mazerunner.maze.logic.user;
import java.util.UUID;

public class User implements IUser, Player {

    private final UUID id;
    private final String name;
    private int coinsCollected;
    private int[] spawnPosition;

    public User(UUID id, String name){
        this.id = id;
        this.name = name;
    }

    // Update user to database.
    public void update(){
        throw new UnsupportedOperationException();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCoinsCollected() {
        return coinsCollected;
    }

    public void incrementCoinsCollected() {
        this.coinsCollected++;
    }
}
