package com.mazerunner.maze.sockets.messages;

public enum ServerMessages {
    Welcome,
    LobbyCreatedSuccess,
    LobbyCreatedFail,
    LobbyJoinNotFound,
    LobbyJoinSuccess,
    LobbyJoinFail,
    SpawnPlayer,
    SpawnItems,
    PlayerMovement,
    GameStart,
    ItemsLeft,
    NoItemsLeft,
    GameResults
}
