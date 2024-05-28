import java.util.Arrays;
import java.util.Random;

public class NQueensHillClimbing {
    static final int N = 8;

    public static void main(String[] args) {
        int[] state = new int[N];
        configureRandomly(state);
        hillClimbing(state);
    }

    // Randomly configure the initial state
    static void configureRandomly(int[] state) {
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            state[i] = rand.nextInt(N);
        }
    }

    // Print the board
    static void printBoard(int[] state) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(state[j] == i ? " Q " : " . ");
            }
            System.out.println();
        }
    }

    // Calculate the number of attacking pairs
    static int calculateObjective(int[] state) {
        int attacking = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (state[i] == state[j] || Math.abs(state[i] - state[j]) == Math.abs(i - j)) {
                    attacking++;
                }
            }
        }
        return attacking;
    }

    // Get the best neighbor with the least conflicts
    static void getBestNeighbour(int[] state) {
        int[] bestState = Arrays.copyOf(state, N);
        int bestObjective = calculateObjective(state);

        for (int col = 0; col < N; col++) {
            int originalRow = state[col];
            for (int row = 0; row < N; row++) {
                if (row == originalRow) continue;

                state[col] = row;
                int currentObjective = calculateObjective(state);

                if (currentObjective < bestObjective) {
                    bestObjective = currentObjective;
                    bestState = Arrays.copyOf(state, N);
                }
            }
            state[col] = originalRow;  // Revert to original row
        }
        System.arraycopy(bestState, 0, state, 0, N);
    }

    // Hill climbing algorithm
    static void hillClimbing(int[] state) {
        int currentObjective = calculateObjective(state);

        while (true) {
            getBestNeighbour(state);
            int newObjective = calculateObjective(state);

            if (newObjective >= currentObjective) {
                printBoard(state);
                System.out.println("Objective: " + newObjective);
                break;
            }
            currentObjective = newObjective;
        }
    }
}
