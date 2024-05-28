import java.util.*;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        dfs(0, board, solutions, n);
        return solutions;
    }

    private void dfs(int row, char[][] board, List<List<String>> solutions, int n) {
        if (row == n) {
            solutions.add(constructBoard(board));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(board, row, col, n)) {
                board[row][col] = 'Q';
                dfs(row + 1, board, solutions, n);
                board[row][col] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col, int n) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    private List<String> constructBoard(char[][] board) {
        List<String> boardConfig = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            boardConfig.add(new String(board[i]));
        }
        return boardConfig;
    }
}

public class CSP_NQueens {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Board Size: ");
        int n = scanner.nextInt();
        Solution solution = new Solution();
        List<List<String>> results = solution.solveNQueens(n);

        System.out.println("All solutions for " + n + "-Queens problem:");
        for (List<String> solutionBoard : results) {
            for (String row : solutionBoard) {
                System.out.println(row);
            }
            System.out.println();
        }
        scanner.close();
    }
}
