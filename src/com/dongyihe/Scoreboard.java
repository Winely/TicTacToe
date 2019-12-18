package com.dongyihe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scoreboard {
    private final Map<Player, Integer> scoreboard = new HashMap<>();
    private int stalemateTimes = 0;
    public <T extends Player> Scoreboard(List<T> players){
        for (Player player : players){
            scoreboard.put(player, 0);
        }
    }

    public void addScore(Player player, int bonus){
        scoreboard.put(player, scoreboard.get(player) + bonus);
    }

    public int getScore(Player player){
        return scoreboard.get(player);
    }

    public int getStalemateTimes() {
        return stalemateTimes;
    }

    public void addDrawTimes(int stalemateTimes) {
        this.stalemateTimes += stalemateTimes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Score board:\n");
        for (Player player : scoreboard.keySet()){
            sb.append(String.format("%s: %d\n", player.getName(), scoreboard.get(player)));
        }
        sb.append(String.format("Stalemates: %d\n", stalemateTimes));
        return sb.toString();
    }
}
