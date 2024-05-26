import java.util.*;

public class RiverBFS {

    public static class State {
        int missionariesLeft, cannibalsLeft, boat;
        State parent;

        public State(int ml, int cl, int b, State parent) {
            this.missionariesLeft = ml;
            this.cannibalsLeft = cl;
            this.boat = b;
            this.parent = parent;
        }

        public boolean isGoal() {
            return missionariesLeft == 0 && cannibalsLeft == 0;
        }

        public boolean isValid() {
            if (missionariesLeft >= 0 && cannibalsLeft >= 0 && missionariesLeft <= 3 && cannibalsLeft <= 3) {
                if (missionariesLeft == 0 || missionariesLeft >= cannibalsLeft) {
                    int missionariesRight = 3 - missionariesLeft;
                    int cannibalsRight = 3 - cannibalsLeft;
                    return missionariesRight == 0 || missionariesRight >= cannibalsRight;
                }
            }
            return false;
        }

        public List<State> generateSuccessors() {
            List<State> successors = new ArrayList<>();
            if (boat == 1) {
                addSuccessor(successors, new State(missionariesLeft - 2, cannibalsLeft, 0, this));
                addSuccessor(successors, new State(missionariesLeft, cannibalsLeft - 2, 0, this));
                addSuccessor(successors, new State(missionariesLeft - 1, cannibalsLeft - 1, 0, this));
                addSuccessor(successors, new State(missionariesLeft - 1, cannibalsLeft, 0, this));
                addSuccessor(successors, new State(missionariesLeft, cannibalsLeft - 1, 0, this));
            } else {
                addSuccessor(successors, new State(missionariesLeft + 2, cannibalsLeft, 1, this));
                addSuccessor(successors, new State(missionariesLeft, cannibalsLeft + 2, 1, this));
                addSuccessor(successors, new State(missionariesLeft + 1, cannibalsLeft + 1, 1, this));
                addSuccessor(successors, new State(missionariesLeft + 1, cannibalsLeft, 1, this));
                addSuccessor(successors, new State(missionariesLeft, cannibalsLeft + 1, 1, this));
            }
            return successors;
        }

        private void addSuccessor(List<State> successors, State state) {
            if (state.isValid()) {
                successors.add(state);
            }
        }
    }

    public static void BFS(State initialState) {
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        queue.add(initialState);

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.isGoal()) {
                printSolution(current);
                return;
            }

            visited.add(current);

            for (State successor : current.generateSuccessors()) {
                if (!visited.contains(successor) && !queue.contains(successor)) {
                    queue.add(successor);
                }
            }
        }
        System.out.println("No solution found.");
    }

    public static void printSolution(State state) {
        if (state == null) return;
        printSolution(state.parent);
        System.out.println(state.missionariesLeft + "M " + state.cannibalsLeft + "C ~~~~ " 
                            + (3 - state.missionariesLeft) + "M " + (3 - state.cannibalsLeft) + "C");
    }

    public static void main(String[] args) {
        State initialState = new State(3, 3, 1, null);
        BFS(initialState);
    }
}
