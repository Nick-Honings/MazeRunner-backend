package com.mazerunner.maze.sockets.controllers;

import com.mazerunner.maze.logic.maze.Maze;
import com.mazerunner.maze.sockets.messages.inbound.MovementMessage;
import com.mazerunner.maze.sockets.messages.inbound.StartGameMessage;
import com.mazerunner.maze.sockets.messages.outbound.GameSetupMessage;
import com.mazerunner.maze.sockets.messages.outbound.MovementUpdate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

//    // Todo: change to pathvariable id
//    @MessageMapping("/movement")
//    @SendTo("/topic/game/1")
//    public MovementUpdate updatePlayerMovement(@Payload MovementMessage message){
//        System.out.println(message.getMessageType());
//        System.out.println(message.getArgs());
//        return new MovementUpdate(message.getArgs());
//    }

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
