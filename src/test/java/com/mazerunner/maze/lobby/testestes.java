package com.mazerunner.maze.lobby;

import org.junit.jupiter.api.Test;


public class testestes {

    @Test
    public void testByteArray(){
        String url = "/topic/lobbyInfo/XWLMVLDV";
        byte[] urlBytesRight = new byte[]{47, 116, 111, 112, 105, 99, 47, 108, 111, 98, 98, 121, 73, 110, 102, 111, 47, 88, 87, 76, 77, 86, 76, 68, 86};
        byte[] urlBytesWrong = new byte[]{47, 0, 116, 0, 111, 0, 112, 0, 105, 0, 99, 0, 47, 0, 108, 0, 111, 0, 98, 0, 98, 0, 121, 0, 73, 0, 110, 0, 102, 0, 111, 0, 47, 0, 88, 0, 87, 0,76, 0, 77, 0, 86, 0, 76, 0, 68, 0, 86, 0, 11, 32};
        int bufferLength = (urlBytesWrong.length / 2) - 1;
        byte[] buffer = new byte[urlBytesRight.length - 1];
        int bytesAdded = 0;



        for(int i = 0; i < urlBytesWrong.length - 4; i++){
            if(urlBytesWrong[i] != 0){
                buffer[bytesAdded] = urlBytesWrong[i];
                System.out.println(urlBytesWrong[i]);
                bytesAdded++;
            }
        }
        for(int i = 0; i < buffer.length - 1; i++){
            System.out.println(buffer[i]);
        }
    }
}
