package com.mazerunner.maze.sockets;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;


public class FilterChannelInterceptor implements ChannelInterceptor {

    private String destination = "/topic/lobbyInfo/AAAA";


    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel){
        //StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
        StompHeaderAccessor headerAccessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        System.out.println("TEST");
        assert headerAccessor != null;
        if(headerAccessor.getCommand() == StompCommand.SUBSCRIBE && Objects.requireNonNull(headerAccessor.getDestination()).contains("lobbyInfo")){
            System.out.println("SUBSCRIBE EVENT DETECTED, SANITIZING INPUT");


            if(destination.equals(headerAccessor.getDestination())){
                System.out.println("True");
            }
        }
        return message;
    }

    private String RemoveEmptyBytes(String message){
        char[] codes = message.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (char code : codes) {
            sb.append(code);
        }
        return sb.toString();
    }
}
