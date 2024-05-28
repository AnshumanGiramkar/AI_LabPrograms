import java.util.*;

public class EightPuzzleSolver_AStar {
    
    static void findZero(int[][] board, int[] pos) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    pos[0] = i;
                    pos[1] = j;
                    return;
                }
            }
        }
    }
    
    static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    static boolean isGoalState(int[][] board, int[][] goal) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != goal[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    static int findMisplacedTiles(int[][] board, int[][] goal) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != goal[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
    
    static void aStar(int[][] board, int[][] goal, int depth, int x, int y) {
        PriorityQueue<int[][]> pq = new PriorityQueue<>(Comparator.comparingInt((int[][] b) -> {
            int g = depth;
            int h = findMisplacedTiles(b, goal);
            return g + h;
        }));
        pq.add(board);
        
        while (!pq.isEmpty()) {
            int[][] curr = pq.poll();
            printBoard(curr);
            int[] pos = new int[2];
            findZero(curr, pos);
            int currX = pos[0];
            int currY = pos[1];
            
            if (isGoalState(curr, goal)) {
                System.out.println("Goal State Reached");
                return;
            }
            
            int[] dx = {0, 0, -1, 1};
            int[] dy = {1, -1, 0, 0};
            
            for (int i = 0; i < 4; i++) {
                int newX = currX + dx[i];
                int newY = currY + dy[i];
                if (newX >= 0 && newX < curr.length && newY >= 0 && newY < curr.length) {
                    int[][] next = new int[curr.length][curr.length];
                    for (int j = 0; j < curr.length; j++) {
                        next[j] = curr[j].clone();
                    }
                    swap(next, currX, currY, newX, newY);
                    int currDepth = depth + 1;
                    int currH = findMisplacedTiles(next, goal);
                    pq.add(next);
                }
            }
        }
    }
    
    static void swap(int[][] board, int x1, int y1, int x2, int y2) {
        int temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }
    
    public static void main(String[] args) {
        int[][] initial = {
            {2, 8, 3},
            {1, 6, 4},
            {7, 5, 0}
        };
        int[][] goal = {
            {1, 2, 3},
            {8, 0, 4},
            {7, 6, 5}
        };
        int[] pos = new int[2];
        findZero(initial, pos);
        
        aStar(initial, goal, 0, pos[0], pos[1]);
    }
}

