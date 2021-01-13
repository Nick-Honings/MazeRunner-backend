package com.mazerunner.maze.sockets.messages;

public enum ClientMessages {
    WelcomeReceived,
    CreateLobby,
    JoinLobby,
    StartGame,
    SpawnPlayerSuccess,
    SpawnItemsSuccess,
    GameReady,
    PlayerMovement,
    ItemPickedUp,
    GameEnded,
}
