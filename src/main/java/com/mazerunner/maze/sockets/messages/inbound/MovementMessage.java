package com.mazerunner.maze.sockets.messages.inbound;

import com.mazerunner.maze.sockets.messages.IMessage;
import com.mazerunner.maze.sockets.messages.ServerMessages;

import java.util.ArrayList;
import java.util.List;

public class MovementMessage implements IMessage {

    private final ServerMessages msgType = ServerMessages.PlayerMovement;
    private final Integer playerId;
    private final Integer x;
    private final Integer y;
    private final Integer z;
    private final float rotation;
    private List<String> args;

    public MovementMessage(Integer playerId, Integer x, Integer y, Integer z, float rotation){
        this.playerId = playerId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.rotation = rotation;

        this.args = new ArrayList<>();
        this.args.add(playerId.toString());
        this.args.add(x.toString());
        this.args.add(y.toString());
        this.args.add(z.toString());
        this.args.add(Float.toString(rotation));
    }
}
