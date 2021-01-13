package com.mazerunner.maze.logic.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {



    // Maze properties
    private MazeCell[][] maze;
    private int rows = 2;
    private int columns = 2;
    private int totalCoins;


    // variables that the algorithm uses
    private int currentRow = 0;
    private int currentColumn = 0;
    private final Random rg;
    // Will become true if the algorithm finishes
    private boolean scanCompleted = false;


    public Maze(int rows, int columns, int coins){
        this.rows = rows;
        this.columns = columns;
        this.totalCoins = coins;
        rg = new Random();
    }

    // This is the entry point of the class. When called, this will generate a full maze
    // based on the properties that are passed to the constructor.
    public void Generate(){
        // First, create a grid.
        CreateGrid();
        HuntAndKill();
    }

    // Method to fill the maze array with maze cells.
    private void CreateGrid(){
        maze = new MazeCell[rows][columns];

        // O(n^2) complexity
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                maze[i][j] = new MazeCell();

                // Create a entrance and an exit. For now, this is fixed at the top left and bottom right.
                // In the future, I want to randomize this.
                if(i == 0 && j == 0){
                    // Create an entrance
                    maze[i][j].setHasLeftWall(false);
                }
                if(i == rows - 1 && j == columns - 1){
                    maze[i][j].setHasRightWall(false);
                }
            }
        }
    }

    // This method combines the hunt and kill phase of the algorithm.
    private void HuntAndKill(){
        // Mark the first cell of the random walk as visited.
        maze[currentRow][currentColumn].setVisited(true);

        // Scan will be completed if every cell has been visited.
        while(!scanCompleted){
            Walk();
            Hunt();
        }
    }

    // This method will create a path by "destroying" walls as it moves randomly through the grid.
    private void Walk(){
        // Loop until there are no visited cells left.
        while (HasUnvisitedNeighbours()){

            // Go in a random direction, might change this to an enum later.
            // Todo: chang to enum?
            int direction = rg.nextInt(4);

            // Get the current cell the algorithm is "standing" in.
//            MazeCell currentCell = maze[currentRow][currentRow];

            // Up
            if(direction == 0){

                if(IsCellUnvisited(currentRow - 1, currentColumn)){
                    // Check if there is an up wall
                    if(maze[currentRow][currentColumn].HasUpWall()){
                        maze[currentRow][currentColumn].setHasUpWall(false);
                    }
                    // "Walk" to the next cell
                    currentRow--;
                    maze[currentRow][currentColumn].setVisited(true);

                    // All cells have 4 walls, so just destroying the upp wall is not enough.
                    // It needs to destroy the down wall of the next cell too.
                    if(maze[currentRow][currentColumn].HasDownWall()){
                        maze[currentRow][currentColumn].setHasDownWall(false);
                    }
                }
            }
            // Down
            else if(direction == 1){

                if(IsCellUnvisited(currentRow + 1, currentColumn)){

                    if(maze[currentRow][currentColumn].HasDownWall()){
                        maze[currentRow][currentColumn].setHasDownWall(false);
                    }

                    currentRow++;
                    maze[currentRow][currentColumn].setVisited(true);

                    if(maze[currentRow][currentColumn].HasUpWall()){
                        maze[currentRow][currentColumn].setHasUpWall(false);
                    }
                }
            }
            // Left
            else if(direction == 2){

                if(IsCellUnvisited(currentRow, currentColumn - 1)){

                    if(maze[currentRow][currentColumn].HasLeftWall()){
                        maze[currentRow][currentColumn].setHasLeftWall(false);
                    }

                    currentColumn--;
                    maze[currentRow][currentColumn].setVisited(true);

                    if(maze[currentRow][currentColumn].HasRightWall()){
                        maze[currentRow][currentColumn].setHasRightWall(false);
                    }
                }
            }
            // Right
            else if(direction == 3){

                if(IsCellUnvisited(currentRow, currentColumn + 1)){

                    if(maze[currentRow][currentColumn].HasRightWall()){
                        maze[currentRow][currentColumn].setHasRightWall(false);
                    }

                    currentColumn++;
                    maze[currentRow][currentColumn].setVisited(true);

                    if(maze[currentRow][currentColumn].HasLeftWall()){
                        maze[currentRow][currentColumn].setHasLeftWall(false);
                    }
                }
            }
        }

    }

    // Check if the maze still has unvisited cells. If there are any, it means that the
    // algorithm has not created a full path.
    private boolean HasUnvisitedNeighbours(){
        if(IsCellUnvisited(currentRow - 1, currentColumn)){
            return true;
        }
        else if(IsCellUnvisited(currentRow + 1, currentColumn)){
            return true;
        }
        else if(IsCellUnvisited(currentRow, currentColumn - 1)){
            return true;
        }
        else if(IsCellUnvisited(currentRow, currentColumn + 1)){
            return true;
        }
        return false;
    }

    private boolean IsCellUnvisited(int row, int column){
        // Boundary and visited check
        if(row >= 0 && row < rows && column >= 0 && column < columns && !maze[row][column].isVisited()){
            return true;
        }
        return false;
    }

    private boolean HasCellVisitedNeighbours(int row, int column){
        // Check up
        if(row > 0 && maze[row -1][column].isVisited()){
            return true;
        }
        // Check down
        else if(row < rows - 1 && maze[row + 1][column].isVisited()){
            return true;
        }
        // Check left
        else if(column > 0 && maze[row][column - 1].isVisited()){
            return true;
        }
        // Check right
        else if(column < columns -1 && maze[row][column + 1].isVisited()){
            return true;
        }

        return false;
    }

    private void Hunt() {
        // Flip scan completed
        scanCompleted = true;

        for(int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if(!maze[i][j].isVisited() && HasCellVisitedNeighbours(i, j)){

                    scanCompleted = false;
                    currentRow = i;
                    currentColumn = j;
                    maze[currentRow][currentColumn].setVisited(true);
                    // Destroy a random adjacent wall
                    DestroyAdjacentWall();
                    return;
                }
            }
        }
    }

    // This wil connect cells with each other, based on if the cell is visited or not.
    // Destroy a random wall in any direction.
    private void DestroyAdjacentWall() {
        boolean destroyed = false;

        while(!destroyed){
            int direction = rg.nextInt(4);


            // Check up
            if (direction == 0)
            {
                if(currentRow > 0 && maze[currentRow -1][currentColumn].isVisited())
                {
                    if (maze[currentRow][currentColumn].HasUpWall())
                    {
                        maze[currentRow][currentColumn].setHasUpWall(false);

                    }
                    if (maze[currentRow - 1][currentColumn].HasDownWall())
                    {
                        maze[currentRow - 1][currentColumn].HasDownWall();

                    }
                    destroyed = true;
                }
            }
            // Check down
            else if (direction == 1)
            {
                if (currentRow  < rows -1 && maze[currentRow + 1][currentColumn].isVisited())
                {
                    if (maze[currentRow][currentColumn].HasDownWall())
                    {
                        maze[currentRow][currentColumn].setHasDownWall(false);

                    }
                    if(maze[currentRow][currentColumn].HasUpWall())
                    {
                        maze[currentRow + 1][currentColumn].setHasUpWall(false);
                    }
                    destroyed = true;
                }
            }
            // Check left
            else if (direction == 2)
            {
                if (currentColumn > 0 && maze[currentRow][currentColumn - 1].isVisited())
                {
                    if (maze[currentRow][currentColumn].HasLeftWall())
                    {
                        maze[currentRow][currentColumn].setHasLeftWall(false);

                    }
                    if(maze[currentRow][currentColumn - 1].HasRightWall())
                    {
                        maze[currentRow][currentColumn - 1].setHasRightWall(false);
                    }
                    destroyed = true;
                }
            }
            // Check right
            // Todo: check this weird error(not true, so idk why this is)
            else if (direction == 3)
            {
                if (currentColumn < columns - 1  && maze[currentRow][currentColumn + 1].isVisited())
                {
                    if (maze[currentRow][currentColumn].HasRightWall())
                    {
                        maze[currentRow][currentColumn].setHasRightWall(false);

                    }
                    if(maze[currentRow][currentColumn + 1].HasLeftWall())
                    {
                        maze[currentRow][currentColumn + 1].setHasLeftWall(false);
                    }
                    destroyed = true;
                }
            }
        }
    }


    public MazeCell[][] getMaze() {
        return maze;
    }

    public String getMazeString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                sb.append(maze[i][j]);
            }
        }
        return sb.toString();
    }


    public List<String> getWallsForSending(){
        List<String> output = new ArrayList<>();

        for (int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){
                output.add(maze[i][j].getWallValues());
            }
        }
        return output;
    }


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getTotalCoins() {
        return totalCoins;
    }
}
