import java.util.*;

public class NonAI_TicTacToe {

    static String[] board; // Array to hold the board positions
    static String currentPlayer; // To keep track of whose turn it is

    // Method to check if there's a winner or a draw
    static String checkWinner() {
        String[] winningPatterns = {
            board[0] + board[1] + board[2], // Top row
            board[3] + board[4] + board[5], // Middle row
            board[6] + board[7] + board[8], // Bottom row
            board[0] + board[3] + board[6], // Left column
            board[1] + board[4] + board[7], // Middle column
            board[2] + board[5] + board[8], // Right column
            board[0] + board[4] + board[8], // Left diagonal
            board[2] + board[4] + board[6]  // Right diagonal
        };

        // Check if any pattern matches "XXX" or "OOO"
        for (String pattern : winningPatterns) {
            if (pattern.equals("XXX")) return "X";
            if (pattern.equals("OOO")) return "O";
        }

        // Check for draw (if all positions are filled)
        for (int i = 0; i < 9; i++) {
            if (board[i].equals(String.valueOf(i + 1))) {
                return null; // The game continues
            }
        }

        return "draw"; // If no empty spots, it's a draw
    }

    // Method to print the board
    static void printBoard() {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("|---|---|---|");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        board = new String[9]; // Initialize the board
        currentPlayer = "X"; // X always starts
        String winner = null; // To keep track of the winner

        // Initialize the board with numbers 1 to 9
        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i + 1);
        }

        System.out.println("Welcome to 3x3 Tic Tac Toe.");
        printBoard();
        System.out.println("X will play first. Enter a slot number to place X in:");

        while (winner == null) {
            int slot;
            try {
                slot = scanner.nextInt(); // Read user input
                if (slot < 1 || slot > 9) {
                    System.out.println("Invalid input; re-enter slot number:");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; re-enter slot number:");
                scanner.next(); // Clear the invalid input
                continue;
            }

            // Check if the chosen slot is available
            if (board[slot - 1].equals(String.valueOf(slot))) {
                board[slot - 1] = currentPlayer; // Place the mark

                // Switch player
                currentPlayer = currentPlayer.equals("X") ? "O" : "X";

                printBoard();
                winner = checkWinner(); // Check for a winner
            } else {
                System.out.println("Slot already taken; re-enter slot number:");
            }
        }

        // Announce the result
        if (winner.equals("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        } else {
            System.out.println("Congratulations! " + winner + " has won! Thanks for playing.");
        }

        scanner.close();
    }
}
