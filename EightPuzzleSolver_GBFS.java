import java.util.*;

public class EightPuzzleSolver_GBFS {

    static class State {
        int[][] board;
        List<Pair<Integer, Integer>> path;
        int heuristic;

        public State(int[][] board, List<Pair<Integer, Integer>> path, int heuristic) {
            this.board = board;
            this.path = path;
            this.heuristic = heuristic;
        }
    }

    static void printPath(List<Pair<Integer, Integer>> path) {
        for (Pair<Integer, Integer> pair : path) {
            System.out.println(pair.first + " " + pair.second);
        }
        System.out.println();
    }

    static boolean isGoalState(int[][] board, int[][] goal) {
        return Arrays.deepEquals(board, goal);
    }

    static int calculateMisplacedTiles(int[][] board, int[][] goal) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != goal[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    static void gbfs(int[][] initial, int[][] goal) {
        Queue<State> queue = new PriorityQueue<>(Comparator.comparingInt(s -> s.heuristic));
        Set<String> visited = new HashSet<>();
        int initialHeuristic = calculateMisplacedTiles(initial, goal);
        queue.offer(new State(initial, new ArrayList<>(), initialHeuristic));

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            int[][] currentBoard = currentState.board;
            List<Pair<Integer, Integer>> currentPath = currentState.path;

            if (isGoalState(currentBoard, goal)) {
                printPath(currentPath);
                return;
            }

            int n = currentBoard.length;
            int m = currentBoard[0].length;

            int[] dx = {0, 0, -1, 1};
            int[] dy = {-1, 1, 0, 0};

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (currentBoard[i][j] == 0) {
                        for (int k = 0; k < 4; k++) {
                            int newX = i + dx[k];
                            int newY = j + dy[k];
                            if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                                int[][] newBoard = new int[n][m];
                                for (int x = 0; x < n; x++) {
                                    newBoard[x] = currentBoard[x].clone();
                                }
                                swap(newBoard, i, j, newX, newY);
                                int heuristic = calculateMisplacedTiles(newBoard, goal);
                                List<Pair<Integer, Integer>> newPath = new ArrayList<>(currentPath);
                                newPath.add(new Pair<>(newX, newY));
                                String boardString = Arrays.deepToString(newBoard);
                                if (!visited.contains(boardString)) {
                                    visited.add(boardString);
                                    queue.offer(new State(newBoard, newPath, heuristic));
                                }
                            }
                        }
                    }
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
        Scanner scanner = new Scanner(System.in);

        int[][] initial = new int[3][3];
        int[][] goal = new int[3][3];

        System.out.println("Enter initial state:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                initial[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter final state:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                goal[i][j] = scanner.nextInt();
            }
        }

        gbfs(initial, goal);
    }

    static class Pair<A, B> {
        A first;
        B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }
}
