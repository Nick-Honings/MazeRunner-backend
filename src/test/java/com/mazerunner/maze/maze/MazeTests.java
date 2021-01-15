package com.mazerunner.maze.maze;


import com.mazerunner.maze.domain.maze.Maze;
import com.mazerunner.maze.domain.maze.MazeCell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTests {

    private Maze maze;

    @BeforeEach
    void BeforeEach(){

    }


    @Test
    void CreateGrid_DoesCreateAllCells(){
        // Arrange
        maze = new Maze(5,5,1);

        // Act
        maze.Generate();
        MazeCell[][] output = maze.getMaze();

        // Assert
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if(output[i][j] == null){
                    assertFalse(false);
                }
            }
        }
        assertTrue(true);
    }

    @Test
    void CreateGrid_DoesCreateCorrectDimensions(){
        // Arrange
        maze = new Maze(5, 5, 1);

        // Act
        maze.Generate();
        MazeCell[][] output = maze.getMaze();

        // Assert
        // Not all arrays are needed to test. If it does one correct, we can consider it succeeded.
        Assertions.assertEquals(5, output.length);
        Assertions.assertEquals(5, output[0].length);
    }

    @Test
    void CreateGrid_DoesCreateExits(){
        // Arrange
        maze = new Maze(5, 5, 1);

        // Act
        maze.Generate();
        MazeCell[][] output = maze.getMaze();

        // Assert
        assertFalse(output[0][0].HasLeftWall());
        assertFalse(output[4][4].HasRightWall());
    }

    @Test
    void Maze_DoesNotHaveClosedCells(){
        // Arrange
        maze = new Maze(5, 5, 1);
        boolean closedCells = false;

        // Act
        maze.Generate();
        MazeCell[][] output = maze.getMaze();

        // Assert
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                MazeCell toCheck = output[i][j];

                if(toCheck.HasDownWall() && toCheck.HasUpWall() && toCheck.HasRightWall() && toCheck.HasLeftWall()){
                    closedCells = true;
                }
            }
        }
        assertFalse(closedCells);
    }

    @Test
    void Maze_DoesOnlyHaveTwoExits(){
        // Arrange
        maze = new Maze(4, 4, 0);
        int exitCount = 0;

        // Act
        maze.Generate();
        MazeCell[][] output = maze.getMaze();

        // Assert
        // Check left border,
        for (int i = 0; i < 4; i++){
            MazeCell toCheck = output[i][0];
            if(!toCheck.HasLeftWall()){
                exitCount++;
            }
        }
        // Check up border, excluding first cell.
        for (int i = 1; i < 4; i++){
            MazeCell toCheck = output[0][i];
            if(!toCheck.HasUpWall()){
                exitCount++;
            }
        }
        // Check right border, excluding first cell.
        for (int i = 1;  i < 4; i++){
            MazeCell toCheck = output[i][3];
            if(!toCheck.HasRightWall()){
                exitCount++;
            }
        }
        // Check down border, excluding first cell and last cell.
        for (int i = 1;  i < 4; i++){
            MazeCell toCheck = output[3][i];
            if(!toCheck.HasDownWall()){
                exitCount++;
            }
        }
        assertEquals(2, exitCount);
    }

    @Test
    void TestPrintBooleans(){
        maze = new Maze(4,4,0);
        maze.Generate();

        MazeCell cell = new MazeCell();
        //System.out.println(cell.getWallValues());

//        List<boolean[]> t = maze.getWallsForSending();
//        t.forEach(System.out::println);
//
        System.out.println(maze.getWallsForSending());
    }


    @Test
    void TestPrintGrid(){
        maze = new Maze(3,3,1);
        maze.Generate();
        MazeCell[][] output = maze.getMaze();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(output[i][j].HasUpWall()){
                    builder.append("###");
                }
                else{
                    builder.append("   ");
                }
            }
            builder.append(System.lineSeparator());
            for (int j = 0; j < 3; j++){
                if(output[i][j].HasLeftWall()){
                    builder.append("# ");
                }
                else{
                    builder.append("  ");
                }
            }
            for (int j = 0; j < 3; j++){
                if(output[i][j].HasRightWall()){
                    builder.append("#");
                }
                else{
                    builder.append(" ");
                }
            }
            builder.append(System.lineSeparator());
            for (int j = 0; j < 3; j++){
                if(output[i][j].HasDownWall()){
                    builder.append("###");
                }
                else{
                    builder.append("   ");
                }
            }
            builder.append(System.lineSeparator());
        }

        System.out.println(builder);


    }

}
