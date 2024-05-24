import java.util.Scanner;

public class TicTacToeAI {
    private static final char HUMAN_PLAYER = 'X';
    private static final char AI_PLAYER = 'O';

    public static void main(String[] args) {
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBoard(board);
            humanMove(board);

            if (isWinner(board, HUMAN_PLAYER)) {
                printBoard(board);
                System.out.println("You win!");
                break;
            } else if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("It's a draw!");
                break;
            }

            aiMove(board);

            if (isWinner(board, AI_PLAYER)) {
                printBoard(board);
                System.out.println("AI wins!");
                break;
            } else if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("It's a draw!");
                break;
            }
        }

        scanner.close();
    }

    // Method to print the current state of the board
    private static void printBoard(char[][] board) {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println("\n  -----");
        }
    }

    // Method to handle the human player's move
    private static void humanMove(char[][] board) {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        while (true) {
            System.out.println("Enter your move (row and column): ");
            row = scanner.nextInt();
            col = scanner.nextInt();

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = HUMAN_PLAYER;
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    // Method to handle the AI's move
    private static void aiMove(char[][] board) {
        int[] bestMove = findBestMove(board);
        board[bestMove[0]][bestMove[1]] = AI_PLAYER;
    }

    // Method to find the best move for the AI using the minimax algorithm
    private static int[] findBestMove(char[][] board) {
        int[] bestMove = {-1, -1};
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = AI_PLAYER;
                    int score = minimax(board, false);
                    board[i][j] = ' ';

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{i, j};
                    }
                }
            }
        }

        return bestMove;
    }

    // Minimax algorithm to evaluate the board
    private static int minimax(char[][] board, boolean isMaximizing) {
        if (isWinner(board, HUMAN_PLAYER)) return -1;
        if (isWinner(board, AI_PLAYER)) return 1;
        if (isBoardFull(board)) return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = isMaximizing ? AI_PLAYER : HUMAN_PLAYER;
                    int score = minimax(board, !isMaximizing);
                    board[i][j] = ' ';
                    bestScore = isMaximizing ? Math.max(bestScore, score) : Math.min(bestScore, score);
                }
            }
        }

        return bestScore;
    }

    // Method to check if a player has won
    private static boolean isWinner(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;

        return false;
    }

    // Method to check if the board is full
    private static boolean isBoardFull(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') return false;
            }
        }
        return true;
    }
}
