package com.dongyihe;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicTacToeGame extends BoardGame {
    private final int dim;

    public TicTacToeGame(int dim, List<BoardGamePlayer> players){
        super("TicTacToe", new SquareBoard(dim), players);
        this.dim = dim;
    }

    public TicTacToeGame(int dim, String playerName1, String playerName2) {
        this(dim, Arrays.asList(
            new BoardGamePlayer(playerName1, "O"),
            new BoardGamePlayer(playerName2, "X")
        ));
    }

    @Override
    protected BoardGamePlayer checkWinner(BoardGamePlayer player, Tile lastMovedTile) {
        String marker = player.getMarker();
        final int rowNum = lastMovedTile.getX();
        final int columnNum = lastMovedTile.getY();
        // check horizontal match
        if (board.getTiles().get(rowNum).stream().allMatch(
                tile -> tile.getState().equals(marker))) {
            return player;
        }
        // check vertical match
        if (board.getTiles().stream().allMatch(
                row -> row.get(columnNum).getState().equals(marker))) {
            return player;
        }
        // check diagonal match "\"
        if (rowNum == columnNum) {
            boolean flag = true;
            for (int i = 0; i < this.dim; i++){
                flag &= (board.getTile(i, i).getState().equals(marker));
            }
            if (flag) return player;
        }
        // check diagonal match "/"
        if (rowNum + columnNum == this.dim - 1) {
            boolean flag = true;
            for (int i = 0; i < this.dim; i++){
                flag &= (board.getTile(i, this.dim - i - 1).getState().equals(marker));
            }
            if (flag) return player;
        }
        return this.STALEMATES;
    }

    @Override
    protected boolean isEnd() {
        return board.isFull();
    }

    @Override
    protected void winnerFound(BoardGamePlayer winner) {
        scoreboard.addScore(winner, 1);
        System.out.printf("User %s win.\n", winner.getName());
    }

    @Override
    protected Tile getNextTile(Scanner sc){
        Tile tile;
        while (true){
            System.out.printf("%s Enter your move: ", this.getCurrentPlayer().getName());
            String input  = sc.next();
            tile = getTileWithXYInput(input);
            if (tile == null) {
                tile = getTileWithIndexInput(input);
            }
            if (tile == null) {
                System.out.printf("Err: invalid input %s\n", input);
            } else {
                if (!tile.isEmpty()) {
                    System.out.printf("Err: Tile (%s) is not empty: [%s]\n", input, tile.getState());
                }
                else return tile;
            }
        }
    }

    /***
     * Get the tile with input like "1,1".
     * @param input input string
     * @return Tile object if input is valid; null if invalid
     */
    private Tile getTileWithXYInput(String input){
        final Pattern p = Pattern.compile("(\\d+),(\\d+)");
        Matcher matcher1 = p.matcher(input);
        if (matcher1.matches()) {
            int x = Integer.parseInt(matcher1.group(1));
            int y = Integer.parseInt(matcher1.group(2));
            if (x < 1 || x > dim){
                System.out.printf("Err: row number %d out of range [1, %d]\n", x, dim);
            }
            else if (y < 1 || y > dim){
                System.out.printf("Err: column number %d out of range [1, %d]\n", y, dim);
            }
            else {
                return this.board.getTile(x - 1, y - 1);
            }
        }
        return null;
    }

    /***
     * Get tile object with index input like "5".
     * "5" is equivalent with "2,2"
     * @param input input string
     * @return Tile object if input is valid; if invalid return null
     */
    private Tile getTileWithIndexInput(String input){
        final Pattern p = Pattern.compile("(\\d+)");
        Matcher matcher = p.matcher(input);
        if (matcher.matches()) {
            int i = Integer.parseInt(matcher.group(1));
            if (i < 1 || i > dim * dim) {
                System.out.printf("Err: Tile index %d out of range [1, %d]\n", i, dim * dim);
            } else {
                return this.board.getTile((i-1) / dim, (i-1) % dim);
            }
        }
        return null;
    }
}
