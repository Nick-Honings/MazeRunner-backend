package com.mazerunner.maze.domain.lobby;

import com.mazerunner.maze.domain.maze.Maze;
import com.mazerunner.maze.domain.user.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {

    private UUID id;
    private long elapsedSeconds;
    private List<Player> players;
    private boolean hasGameEnded;
    private Maze maze;
    private int totalCoins = 5;

    public Game(){
        players = new ArrayList<>();
        this.setUpGame();
    }

    // Used to setup the maze and player spawns
    private void setUpGame(){
        throw new UnsupportedOperationException();
    }

    private void checkIfEndGame(){}

    // Called every time a player collects a coin. If a player collected all coins, send end game for all users.
    private void checkIfWinner(){
        Player player = this.players.stream()
                .filter(p -> p.getCoinsCollected() == this.totalCoins)
                .findFirst()
                .orElse(null);

        if(player != null){
            // Player has collected all the coins and can cross the finish.

        }
    }

    // Called whenever a player collects a coin
    private void incrementCoins(String id){
        if(id != null){
            this.players.forEach(p -> {
                if(p.getId() == id){
                    p.incrementCoinsCollected();
                }
            });
        }
    }

    // Todo: separate this and use dependency injection. Also, maybe do not make coins static, but chosen by the player?
    private void setMazeDimensions(int mazeWidth, int mazeHeight){
        this.maze = new Maze(mazeWidth, mazeHeight, this.totalCoins);
    }



}
