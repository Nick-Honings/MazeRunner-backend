package com.mazerunner.maze.lobby;

import com.mazerunner.maze.logic.lobby.Lobby;
import com.mazerunner.maze.logic.lobby.LobbyManager;
import com.mazerunner.maze.logic.user.Player;
import com.mazerunner.maze.logic.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class LobbyManagerTests {

    private LobbyManager manager;

    @BeforeEach
    public void setup(){
        manager = new LobbyManager();
    }


    @Test
    public void addLobby_ShouldWork(){
        // Arrange
        Integer expected = 1;
        Lobby lobby = new Lobby();

        // Act
        boolean added = manager.addLobby(lobby);
        Integer result = manager.getLobbies().size();

        // Assert
        Assertions.assertEquals(expected, result);
        Assertions.assertTrue(added);
    }

    @Test
    public void addLobby_ShouldNotAllowNull(){
        // Arrange
        Integer expected = 0;
        Lobby lobby = null;

        // Act
        boolean added = manager.addLobby(lobby);
        Integer result = manager.getLobbies().size();

        // Assert
        Assertions.assertEquals(expected, result);
        Assertions.assertFalse(added);

    }

    @Test
    public void removeLobby_ShouldWork(){
        // Arrange
        Integer expected = 0;
        Lobby lobby = new Lobby();
        String lobbyUuid = lobby.getId();
        manager.addLobby(lobby);

        // Act
        boolean removed = manager.removeLobby(lobbyUuid);
        Integer result = manager.getLobbies().size();

        // Assert
        Assertions.assertEquals(expected, result);
        Assertions.assertTrue(removed);
    }

    @Test
    public void removeLobby_ShouldWorkIfMoreLobbies(){
        Integer expected = 1;
        Lobby lobby1 = new Lobby();
        String lobbyUuid = lobby1.getId();
        manager.addLobby(lobby1);

        Lobby lobby2 = new Lobby();
        manager.addLobby(lobby2);
        // Act
        boolean removed = manager.removeLobby(lobbyUuid);
        Integer result = manager.getLobbies().size();

        // Assert
        Assertions.assertEquals(expected, result);
        Assertions.assertTrue(removed);
        Assertions.assertEquals(lobby2, manager.getLobbies().get(0));
    }


    @Test
    public void removeLobby_ShouldNotAllowNull(){
        // Arrange
        Integer expected = 0;
        Lobby lobby = new Lobby();
        String lobbyUuid = lobby.getId();

        // Act
        boolean removed = manager.removeLobby(lobbyUuid);
        Integer result = manager.getLobbies().size();

        // Assert
        Assertions.assertEquals(expected, result);
        Assertions.assertFalse(removed);
    }

    @Test
    public void removeLobby_ShouldNotRemoveIfInvalidId(){
        // Arrange
        Integer expected = 1;
        Lobby lobby = new Lobby();
        String lobbyUuid = UUID.randomUUID().toString();
        manager.addLobby(lobby);

        // Act
        boolean removed = manager.removeLobby(lobbyUuid);
        Integer result = manager.getLobbies().size();

        // Assert
        Assertions.assertEquals(expected, result);
        Assertions.assertFalse(removed);
    }

    @Test
    public void getById_ShouldWork(){
        // Arrange
        Lobby expected = new Lobby();
        String lobbyUuid = expected.getId();
        manager.addLobby(expected);

        // Act
        Lobby result = manager.getById(lobbyUuid);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getById_ShouldNotWorkIfNull(){
        // Arrange
        Lobby lobby = new Lobby();
        String lobbyUuid = null;
        manager.addLobby(lobby);

        // Act
        Lobby result = manager.getById(lobbyUuid);

        // Assert
        Assertions.assertNull(result);
        Assertions.assertEquals(1, manager.getLobbies().size());
    }

    @Test
    public void getById_ShouldNotWorkIfInvalidId(){
        // Arrange
        Lobby lobby = new Lobby();
        String lobbyUuid = UUID.randomUUID().toString();
        manager.addLobby(lobby);

        // Act
        Lobby result = manager.getById(lobbyUuid);

        // Assert
        Assertions.assertNull(result);
        Assertions.assertEquals(1, manager.getLobbies().size());
    }

    @Test
    public void getByLobbyCode_ShouldWork(){
        // Arrange
        Lobby expected = new Lobby();
        String lobbyCode = expected.getLobbyCode();
        manager.addLobby(expected);

        // Act
        Lobby result = manager.getByLobbyCode(lobbyCode);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void test_Test(){
        Lobby lobby = new Lobby();
        String lobbyCode = lobby.getLobbyCode();
        Player player = new User("1234", "Test");
        lobby.addPlayer(player);
        manager.addLobby(lobby);

        boolean result = manager.getByLobbyCode(lobbyCode).removePlayer(player.getId());
        Assertions.assertTrue(result);

    }
}
