package com.mazerunner.maze.domain.maze;

import java.util.Arrays;

public class MazeCell {

    private boolean visited = false;
    private boolean hasLeftWall = true;
    private boolean hasRightWall = true;
    private boolean hasUpWall = true;
    private boolean hasDownWall = true;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean HasLeftWall() {
        return hasLeftWall;
    }

    public void setHasLeftWall(boolean hasLeftWall) {
        this.hasLeftWall = hasLeftWall;
    }

    public boolean HasRightWall() {
        return hasRightWall;
    }

    public void setHasRightWall(boolean hasRightWall) {
        this.hasRightWall = hasRightWall;
    }

    public boolean HasUpWall() {
        return hasUpWall;
    }

    public void setHasUpWall(boolean hasUpWall) {
        this.hasUpWall = hasUpWall;
    }

    public boolean HasDownWall() {
        return hasDownWall;
    }

    public void setHasDownWall(boolean hasDownWall) {
        this.hasDownWall = hasDownWall;
    }

    @Override
    public String toString() {
        return "{" + hasUpWall + "," + hasLeftWall + "," + hasDownWall + "," + hasRightWall + "}";
    }

    public String getWallValues(){
        return Arrays.toString(new boolean[]{hasUpWall, hasLeftWall, hasDownWall, hasRightWall});
    }

}
