package com.mazerunner.maze.logic.user;
import java.util.UUID;

public class User implements IUser, Player {

    private final String id;
    private final String name;
    private int coinsCollected;
    private int[] spawnPosition;

    public User(String id, String name){
        this.id = id;
        this.name = name;
    }

    // Update user to database.
    public void update(){
        throw new UnsupportedOperationException();
    }

    public String getId() {
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

    public int[] getSpawnPosition() {
        return spawnPosition;
    }

    public void setSpawnPosition(int[] spawnPosition) {
        this.spawnPosition = spawnPosition;
    }
}
