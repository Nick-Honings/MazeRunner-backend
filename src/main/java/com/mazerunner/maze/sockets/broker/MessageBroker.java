package com.mazerunner.maze.sockets.broker;

import com.mazerunner.maze.sockets.messages.ClientMessages;
import com.mazerunner.maze.sockets.messages.IMessage;

import java.util.HashMap;
import java.util.Map;

public class MessageBroker {

    private final Map<ClientMessages, Action<IMessage>> messageHandlers;


    public MessageBroker() {
        this.messageHandlers = new HashMap<>();
        this.InitPacketHandlers();
    }

    private void InitPacketHandlers() {
        messageHandlers.put(ClientMessages.PlayerMovement, new Action<>(MessageHandler::onPlayerMovementReceived));
    }
}
