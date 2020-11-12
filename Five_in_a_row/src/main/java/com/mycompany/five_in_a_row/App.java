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
            game.printBoard();
            int[] moveCoords = game.getMove();
            if (moveCoords == null) {
                gameIsInProgress = false;
                System.out.println("Quiting game! Have a nice day!");
            } else {
                System.out.println("The given coords are valid: " + moveCoords[0] + ", " + moveCoords[1]);
                game.mark(moveCoords, player);
                player = player == Player.X ? Player.O : Player.X;
            }
        }
    }
}
