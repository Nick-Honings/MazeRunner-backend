package com.mazerunner.maze.sockets.controllers;

import com.mazerunner.maze.logic.lobby.Lobby;
import com.mazerunner.maze.logic.lobby.LobbyManager;
import com.mazerunner.maze.logic.user.Player;
import com.mazerunner.maze.logic.user.User;
import com.mazerunner.maze.sockets.messages.inbound.CreateLobbyMessage;
import com.mazerunner.maze.sockets.messages.inbound.JoinLobbyMessage;
import com.mazerunner.maze.sockets.messages.inbound.LeaveLobbyMessage;
import com.mazerunner.maze.sockets.messages.outbound.LobbyMessage;
import com.mazerunner.maze.sockets.messages.outbound.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;

@Controller
public class LobbyController {

    private LobbyManager lobbyManager;
    private SimpMessagingTemplate msgTemplate;

    @Autowired
    public LobbyController(SimpMessagingTemplate msgTemplate){
        this.msgTemplate = msgTemplate;
        lobbyManager = new LobbyManager();

    }

    @MessageMapping("/createLobby")
    @SendTo("/topic/createLobby")
    public LobbyMessage createLobby(CreateLobbyMessage message){
        Lobby lobby = new Lobby();
        // Todo: find playerbyid from usermanager?
        lobby.addPlayer(new User(message.getPlayerId(), message.getName()));
        lobbyManager.addLobby(lobby);
        System.out.println("createlobby endpoint hit");
        System.out.println(lobby.getLobbyCode());
        System.out.println(lobby.getId());
        System.out.println(lobby.getPlayers().size());
        return new LobbyMessage(lobby);
    }

    private String makeJavaString(String message){
        char[] chars = message.toCharArray();
        StringBuilder k = new StringBuilder();

        for (int i = 0; i < chars.length - 1; i++){
            k.append(chars[i]);
        }
        return k.toString();
    }

    @MessageMapping("/joinLobby/{lobbyCode}")
    @SendTo("/topic/lobbyInfo/{lobbyCode}")
    public LobbyMessage JoinLobby(JoinLobbyMessage message, @DestinationVariable String lobbyCode){
        // Todo: check if correctly added
        System.out.println("joinLobby endpoint hit");


        // Todo: check if added
        Lobby current = lobbyManager.getByLobbyCode(lobbyCode);
        if(current != null){

            if(current.getPlayers().size() < current.getMaxPlayers()){
                // Todo: check if added
                lobbyManager.getByLobbyCode(lobbyCode).addPlayer(new User(message.getPlayerId(), message.getPlayerName()));
            }

            // Todo: send message to lobby creator with user.



            // Todo: add error messages5
            return new LobbyMessage(current);
        }
        return null;
    }

    // Since msgTemplate does not work, adding this method
    @MessageMapping("/lobbyInfo/{lobbyId}")
    @SendTo("/topic/lobbyInfo/{lobbyId}")
    public LobbyMessage getLobbyInfo(@DestinationVariable String lobbyId){
        //Todo: check if user is in lobby
        System.out.println("getLobbyInfo endpoint hit");
        System.out.println(lobbyId);
        Lobby current = lobbyManager.getById(lobbyId);
        return new LobbyMessage(current);
    }


    @MessageMapping("/leaveLobby")
    @SendTo("/topic/leaveLobby")
    public SimpleMessage leaveLobby(LeaveLobbyMessage message) {
        System.out.println(message.getLobbyCode());
        System.out.println(message.getPlayerId());

        String playerId = makeJavaString(message.getPlayerId());

        if(lobbyManager.getByLobbyCode(message.getLobbyCode()).removePlayer(message.getPlayerId())){
            System.out.println("OK returned");
            return new SimpleMessage("OK");
        }
        System.out.println("Size");
        System.out.println(lobbyManager.getByLobbyCode(message.getLobbyCode()).getPlayers().size());
        System.out.println("FAILED returned");
        return new SimpleMessage("OK");
    }

    @MessageMapping("startGame")
    public void startGame(){



    }
}
