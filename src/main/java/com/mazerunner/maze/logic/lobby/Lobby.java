package com.mazerunner.maze.logic.lobby;

import com.mazerunner.maze.logic.user.Player;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Lobby {

    private final String id;
    private final String lobbyCode;
    private final List<Player> players;
    private final Integer minPlayers = 2;
    private final Integer maxPlayers = 4;


    public Lobby(){
        this.id = UUID.randomUUID().toString();
        this.lobbyCode = "AAAA";
        //this.lobbyCode = createLobbyCode();
        this.players = new ArrayList<>();
    }

    private String createLobbyCode() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        int length = 8;

        for(int i = 0; i < length; i++){
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return new String(sb.toString().getBytes(), StandardCharsets.UTF_8);
    }

    // Todo: write tests for max players.
    public boolean addPlayer(Player player){
        if(player != null && this.players.size() <= maxPlayers){
            this.players.add(player);
            return true;
        }
        return false;
    }

    public boolean removePlayer(String id){
        if(id != null){
            return this.players.removeIf(p -> p.getId().equals(id));
        }
        return false;
    }

    public Player getPlayer(String id){
        return this.players.stream()
                .filter(u -> u.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public String getId() {
        return id;
    }

    public String getLobbyCode() {
        return lobbyCode;
    }

    public List<Player> getPlayers(){
        return this.players;
    }

    public Integer getMinPlayers() {
        return minPlayers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }
}
