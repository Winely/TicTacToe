package com.dongyihe;

import java.util.List;
import java.util.Scanner;

/***
 * Abstract all games that with a board and several players.
 * Each player owns a certain marker to mark a tile in the board.
 * All players move in loop.
 */
public abstract class BoardGame {
    protected String gameName;
    protected final Board board;
    protected final List<BoardGamePlayer> players;
    protected final BoardGamePlayer STALEMATES = new BoardGamePlayer("STALEMATES", "");
    protected int currentPlayerIndex = 0;
    protected final Scoreboard scoreboard;

    public BoardGame(String gameName, Board board, List<BoardGamePlayer> players) {
        this.gameName = gameName;
        this.board = board;
        this.players = players;
        this.scoreboard = new Scoreboard(players);
    }

    public void printScoreboard(){
        System.out.print(scoreboard);
    }

    /***
     * A move of the current player:
     * Mark board accordingly, and check if a winner is found.
     * @param tile the target tile of the movement
     * @return true if no winner is found and it is not stalemates; false if the game ends
     */
    protected boolean move(Tile tile){
        BoardGamePlayer player = this.nextPlayer();
        tile.setState(player.getMarker());
        BoardGamePlayer winner = this.checkWinner(player, tile);
        if (winner == this.STALEMATES) {
            if (this.isEnd()) {
                scoreboard.addDrawTimes(1);
                System.out.println("Game end. No one is the winner.");
                return false;
            }
            return true;
        } else {
            this.winnerFound(winner);
            return false;
        }
    }

    protected void reset(){
        this.board.clear();
        this.currentPlayerIndex = 0;
    }

    /***
     * Check if the game have a winner.
     * Pass in last step info for quick judgement.
     * @param player player of last step
     * @param tile last tile being moved
     * @return null if no winner; otherwise return the winner
     */
    protected abstract BoardGamePlayer checkWinner(BoardGamePlayer player, Tile tile);
    protected abstract boolean isEnd();
    protected abstract void winnerFound(BoardGamePlayer winner);
    protected abstract Tile getNextTile(Scanner sc);

    /***
     * Start a new round of game.
     * @param sc console input scanner
     */
    protected void newGame(Scanner sc) {
        this.reset();
        System.out.printf("Welcome to %s\n", this.gameName);
        Tile nextTile;
        do {
            printBoard();
            nextTile = getNextTile(sc);
        } while (move(nextTile));
    }

    /***
     * return current player and make currentPlayerIndex to the next.
     * @return current player
     */
    private BoardGamePlayer nextPlayer() {
        BoardGamePlayer player = getCurrentPlayer();
        this.currentPlayerIndex++;
        if (this.currentPlayerIndex == this.players.size()) {
            this.currentPlayerIndex = 0;
        }
        return player;
    }

    protected void printBoard(){
        System.out.println(board);
    }

    protected BoardGamePlayer getCurrentPlayer(){
        return this.players.get(this.currentPlayerIndex);
    }
}
