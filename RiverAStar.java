import java.util.*;

public class RiverAStar {

    public static class State {
        int missionariesLeft, cannibalsLeft, boat;
        State parent;
        int g; // Cost from start to current state
        int h; // Heuristic cost estimate to goal

        public State(int ml, int cl, int b, State parent) {
            this.missionariesLeft = ml;
            this.cannibalsLeft = cl;
            this.boat = b;
            this.parent = parent;
            this.g = parent == null ? 0 : parent.g + 1;
            this.h = heuristic();
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

        public int heuristic() {
            // Simple heuristic: number of people left on the left side
            return missionariesLeft + cannibalsLeft;
        }

        public int getF() {
            // Total cost function for A* (f = g + h)
            return g + h;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return missionariesLeft == state.missionariesLeft && cannibalsLeft == state.cannibalsLeft && boat == state.boat;
        }

        @Override
        public int hashCode() {
            return Objects.hash(missionariesLeft, cannibalsLeft, boat);
        }
    }

    public static void aStar(State initialState) {
        PriorityQueue<State> frontier = new PriorityQueue<>(Comparator.comparingInt(State::getF));
        Set<State> explored = new HashSet<>();

        frontier.add(initialState);

        while (!frontier.isEmpty()) {
            State current = frontier.poll();

            if (current.isGoal()) {
                printSolution(current);
                return;
            }

            explored.add(current);

            for (State successor : current.generateSuccessors()) {
                if (!explored.contains(successor) && !frontier.contains(successor)) {
                    frontier.add(successor);
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
        aStar(initialState);
    }
}
