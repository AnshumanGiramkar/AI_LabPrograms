import java.util.*;

public class EightPuzzleSolver_DFS {

    static void giveans(int[][] initial, int[][] finalState, int i, int j, List<Pair<Integer, Integer>> ans) {
        if (Arrays.deepEquals(initial, finalState)) {
            for (Pair<Integer, Integer> pair : ans) {
                System.out.println(pair.first + " " + pair.second);
            }
            System.out.println();
            return;
        }
        if (ans.size() > 9) {
            return;
        }
        int n = initial.length;
        int m = initial[0].length;
        if (i + 1 < n && initial[i + 1][j] != finalState[i + 1][j]) {
            ans.add(new Pair<>(i + 1, j));
            swap(initial, i, j, i + 1, j);
            giveans(initial, finalState, i + 1, j, ans);
            swap(initial, i, j, i + 1, j);
            ans.remove(ans.size() - 1);
        }
        if (j + 1 < m && initial[i][j + 1] != finalState[i][j + 1]) {
            ans.add(new Pair<>(i, j + 1));
            swap(initial, i, j, i, j + 1);
            giveans(initial, finalState, i, j + 1, ans);
            swap(initial, i, j, i, j + 1);
            ans.remove(ans.size() - 1);
        }
        if (i - 1 >= 0 && initial[i - 1][j] != finalState[i - 1][j]) {
            ans.add(new Pair<>(i - 1, j));
            swap(initial, i, j, i - 1, j);
            giveans(initial, finalState, i - 1, j, ans);
            swap(initial, i, j, i - 1, j);
            ans.remove(ans.size() - 1);
        }
        if (j - 1 >= 0 && initial[i][j - 1] != finalState[i][j - 1]) {
            ans.add(new Pair<>(i, j - 1));
            swap(initial, i, j, i, j - 1);
            giveans(initial, finalState, i, j - 1, ans);
            swap(initial, i, j, i, j - 1);
            ans.remove(ans.size() - 1);
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
        int[][] finalState = new int[3][3];

        System.out.println("Enter initial state:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                initial[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter final state:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                finalState[i][j] = scanner.nextInt();
            }
        }

        List<Pair<Integer, Integer>> ans = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (initial[i][j] == 0) {
                    giveans(initial, finalState, i, j, ans);
                }
            }
        }
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
