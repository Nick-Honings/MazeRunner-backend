package com.mazerunner.maze.sockets.controllers;

import com.mazerunner.maze.domain.maze.Maze;
import com.mazerunner.maze.sockets.messages.inbound.StartGameMessage;
import com.mazerunner.maze.sockets.messages.outbound.GameSetupMessage;
import com.mazerunner.maze.sockets.messages.outbound.MovementUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller("/game")
public class GameController {


    @Autowired
    public GameController(){

    }

    // Todo: change to pathvariable id, send to specific users with principal and @SendToUser mapping
    @MessageMapping("/movement")
    @SendTo("/topic/game/1")
    public MovementUpdate updatePlayerMovement(MovementUpdate message){

        return message;
    }

    @MessageMapping("/getGameSetup")
    @SendTo("/topic/gameSetup")
    public GameSetupMessage getGameSetup(StartGameMessage message){
        // Todo: check if enough players in lobby.
        System.out.println("@getGameSetup()");

        Maze maze = new Maze(message.getRows(), message.getColumns(),0);
        maze.Generate();
        System.out.println("mazze columns " + maze.getColumns());
        System.out.println("mazze rows " + maze.getRows());

        return new GameSetupMessage(maze);
    }
}
