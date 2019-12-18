package com.dongyihe;

public class BoardGamePlayer extends Player {
    private final String marker;

    public BoardGamePlayer(String name, String marker) {
        super(name);
        this.marker = marker;
    }


    public String getMarker() {
        return marker;
    }
}
