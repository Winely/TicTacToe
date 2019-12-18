package com.dongyihe;

import java.util.Scanner;

/***
 * Main entrance of the game.
 */
public class GameShell {
    private BoardGame game;
    private Scanner scanner = new Scanner(System.in);

    public GameShell(BoardGame game){
        this.game = game;
    }

    public static void main(String[] args) {
        TicTacToeGame ticTacToeGame = new ClassicTicTacToeGame();
        GameShell gameShell = new GameShell(ticTacToeGame);
        gameShell.start();
    }

    public void start() {
        while (true) {
            game.newGame(scanner);
            System.out.print("Do you want to continue? (Y/y) ");
            if (scanner.next().toLowerCase().equals("y")) {
                continue;
            }
            game.printScoreboard();
            System.out.println("Bye!");
            scanner.next();
            break;
        }
    }
}
