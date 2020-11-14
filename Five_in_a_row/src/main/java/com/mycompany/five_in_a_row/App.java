/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.five_in_a_row;

import main_package.Game;
import main_package.Player;

/**
 *
 * @author AbelToth
 */

public class App {
    
    public static void main(String[] args) {
        System.out.println("Hello! Welcome our game!");
        Game game = new Game(25, 20); // Max board size 26 * 100
        Boolean gameIsInProgress = true;
        Player player = Player.X;
        
        while (gameIsInProgress) {
            System.out.println("The next player: " + player.toString());
            game.printBoard();
            int[] moveCoords = game.getMove();
            if (moveCoords == null) {
                gameIsInProgress = false;
                System.out.println("Quiting game! Have a nice day!");
            } else {
                game.mark(moveCoords, player);
                Boolean victory = game.hasWon(5, player, moveCoords);
                if (victory) {
                    game.printBoard();
                    System.out.println(player.toString() + " has won the game!");
                    gameIsInProgress = false;
                }
                player = player == Player.X ? Player.O : Player.X;
            }
        }
    }
}
