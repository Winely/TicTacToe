package com.dongyihe;

public class Tile {
    public static final String EMPTY_STATE = " ";
    private String state;
    private final int x;
    private final int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.state = EMPTY_STATE;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isEmpty() {
        return this.state.equals(EMPTY_STATE);
    }
}
