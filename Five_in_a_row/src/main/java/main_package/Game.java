/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_package;

import java.util.Scanner;
/**
 *
 * @author AbelToth
 */
public class Game {
    private int[][] board;
    
    public Game(int numberOfRows, int numberOfColumns) {
        this.board = new int[numberOfRows][numberOfColumns];
    }
    
    public int[][] getBoard() {
        return this.board;
    }
    
    public void setBoard(int numberOfRows, int numberOfColumns) {
        this.board = new int[numberOfRows][numberOfColumns];
    }
    
    public int[] getMove() {
        Boolean isCoordValid;
        char row;
        int rowLetterPosition;
        int col;
        
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter move coordinates. Eg.: B3");
            String coord = scanner.nextLine();
            
            if ("QUIT".equals(coord.toUpperCase())) {
                return null;
            }
            try {
                row = coord.toUpperCase().charAt(0);
                col = Integer.parseInt(coord.substring(1)) - 1;
                rowLetterPosition = (int)row - 65;
            } catch (Exception e) {
                col = -1; // Set to invalid value
                rowLetterPosition = -1;
            }
            
            isCoordValid = (rowLetterPosition >= 0 && rowLetterPosition <=25 &&  // A - Z
                            col >= 0 && col <= 99 && // 0 - 99
                            rowLetterPosition < this.board.length && // coord is on the board
                            col < this.board[0].length && // coord is on the board
                            this.board[rowLetterPosition][col] == 0); // coord is not taken
        } while(!isCoordValid);
        
        int[] coordsAsInt = {rowLetterPosition, col};
        
        return coordsAsInt;
    }
    
    public void mark(int[] coords, Player player) {
        if (this.board[coords[0]][coords[1]] == 0) { // 0 is for empty field
            if (player == Player.X) {
                this.board[coords[0]][coords[1]] = 1; // 1 is for Player1 (X)
            } else {
                this.board[coords[0]][coords[1]] = 2; // 2 is for Player2 (O)
            }
        }
    }
    
    public Boolean hasWon(int markCountToWin, Player player, int[] lastMarkCoords) {
        int playerMarkCount;
        int playerMarkNumber = player == Player.X ? 1 : 2;

        // up - down
        playerMarkCount = 0;
        for (int i = 0; i < markCountToWin; i++) {
            if (lastMarkCoords[0] >= markCountToWin - 1 &&
                this.board[lastMarkCoords[0] - i][lastMarkCoords[1]] == playerMarkNumber) {
                playerMarkCount++;
                if (playerMarkCount == markCountToWin) {
                    return true;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < markCountToWin; i++) {
            if (lastMarkCoords[0] <= this.board.length - markCountToWin -1 &&
                this.board[lastMarkCoords[0] + i][lastMarkCoords[1]] == playerMarkNumber) {
                playerMarkCount++;
                if (playerMarkCount == markCountToWin) {
                    return true;
                }
            } else {
                break;
            }
        }

        // left - right
        playerMarkCount = 0;
        for (int i = 0; i < markCountToWin; i++) {
            if (lastMarkCoords[1] >= markCountToWin -1 &&
                this.board[lastMarkCoords[0]][lastMarkCoords[1] - i] == playerMarkNumber) {
                playerMarkCount++;
                if (playerMarkCount == markCountToWin) {
                    return true;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < markCountToWin; i++) {
            if (lastMarkCoords[1] <= this.board[0].length - markCountToWin -1 &&
                this.board[lastMarkCoords[0]][lastMarkCoords[1] + i] == playerMarkNumber) {
                playerMarkCount++;
                if (playerMarkCount == markCountToWin) {
                    return true;
                }
            } else {
                break;
            }
        }

        // downLeft - upRight
        playerMarkCount = 0;
        for (int i = 0; i < markCountToWin; i++) {
            if (lastMarkCoords[0] <= this.board.length &&
                lastMarkCoords[1] >= markCountToWin - 1 &&
                this.board[lastMarkCoords[0] + i][lastMarkCoords[1] - i] == playerMarkNumber) {
                playerMarkCount++;
                if (playerMarkCount == markCountToWin) {
                    return true;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < markCountToWin; i++) {
            if (lastMarkCoords[0] >= markCountToWin - 1 &&
                lastMarkCoords[1] <= this.board[0].length &&
                this.board[lastMarkCoords[0] - i][lastMarkCoords[1] + i] == playerMarkNumber) {
                playerMarkCount++;
                if (playerMarkCount == markCountToWin) {
                    return true;
                }
            } else {
                break;
            }
        }

        // upLeft - downRight
        playerMarkCount = 0;
        for (int i = 0; i < markCountToWin; i++) {
            if (lastMarkCoords[0] >= markCountToWin - 1 &&
                lastMarkCoords[1] >= markCountToWin - 1 &&
                this.board[lastMarkCoords[0] - i][lastMarkCoords[1] - i] == playerMarkNumber) {
                playerMarkCount++;
                if (playerMarkCount == markCountToWin) {
                    return true;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < markCountToWin; i++) {
            if (lastMarkCoords[0] <= this.board.length - markCountToWin - 1 &&
                lastMarkCoords[1] <= this.board[0].length &&
                this.board[lastMarkCoords[0] + i][lastMarkCoords[1] + i] == playerMarkNumber) {
                playerMarkCount++;
                if (playerMarkCount == markCountToWin) {
                    return true;
                }
            } else {
                break;
            }
        }

        return false;
    }
    
    public Boolean isFull() {
        for (int row = 0; row < this.board.length; row++) {
            for (int col = 0; col < this.board[row].length; col++) {
                if (this.board[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void printBoard() {
        char[] abc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String output = "   ";
        
        for (int col = 0; col < this.board[0].length; col++){
            if (col < 9) {
                output += (col + 1) + "  ";
            } else {
                output += (col + 1) + " ";
            }
        }
        output += "\n";
        for (int row = 0; row < this.board.length; row++) {
            output += abc[row] + "  ";
            for (int col = 0; col < this.board[row].length; col++) {
                switch (this.board[row][col]) {
                    case 1:
                        output += "X  ";
                        break;
                    case 2:
                        output += "O  ";
                        break;
                    default:
                        output += ".  ";
                        break;
                }
            }
            output += "\n";
        }
        
        System.out.println(output);
    }
}
