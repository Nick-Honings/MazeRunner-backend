package com.mazerunner.maze.lobby;

import com.mazerunner.maze.logic.lobby.Lobby;
import com.mazerunner.maze.logic.user.Player;
import com.mazerunner.maze.logic.user.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class LobbyTests {

    private Lobby lobby;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setup(){
        lobby = new Lobby();
        player1 = new User(UUID.randomUUID().toString(), "Test");
        player2 = new User(UUID.randomUUID().toString(), "Test2");
    }

    @Test
    public void addPlayer_ShouldWork(){
        // Arrange
        Integer expected = 1;

        // Act
        boolean added = lobby.addPlayer(player1);
        Integer result = lobby.getPlayers().size();

        // Assert
        Assertions.assertTrue(added);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void addPlayer_ShouldNotAllowNull(){
        // Arrange
        Integer expected = 0;

        // Act
        boolean added = lobby.addPlayer(null);
        Integer result = lobby.getPlayers().size();

        // Assert
        Assertions.assertFalse(added);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void removePlayer_ShouldWork(){
        // Arrange
        Integer expected = 0;
        lobby.addPlayer(player1);

        // Act
        boolean removed = lobby.removePlayer(player1.getId());
        Integer result = lobby.getPlayers().size();

        // Assert
        Assertions.assertTrue(removed);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void removePlayer_ShouldWorkWithString(){
        // Arrange
        Integer expected = 0;
        lobby.addPlayer(player1);
        String playerId = player1.getId();

        // Act
        boolean removed = lobby.removePlayer(playerId);
        Integer result = lobby.getPlayers().size();

        // Assert
        Assertions.assertTrue(removed);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void removePlayer_ShouldWorkIfMorePlayers(){
        // Arrange
        Integer expected = 1;

        lobby.addPlayer(player1);
        lobby.addPlayer(player2);


        // Act
        boolean removed = lobby.removePlayer(player1.getId());
        Integer result = lobby.getPlayers().size();

        // Assert
        Assertions.assertTrue(removed);
        Assertions.assertEquals(expected, result);
        Assertions.assertEquals(player2, lobby.getPlayers().get(0));
    }

    @Test
    public void getPlayer_ShouldWork(){
        // Arrange
        lobby.addPlayer(player1);

        // Act
        Player result = lobby.getPlayer(player1.getId());

        // Assert
        Assertions.assertEquals(player1, result);
    }
}
