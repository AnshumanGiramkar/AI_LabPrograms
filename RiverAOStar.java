import java.util.*;

public class RiverAOStar {

    public static class State {
        int missionariesLeft, cannibalsLeft, boat;
        int missionariesRight, cannibalsRight;
        State parent;
        int g; // Cost from start to current state
        int h; // Heuristic cost estimate to goal
        boolean isSolved;
        List<State> children;
        State bestChild;

        public State(int ml, int cl, int b, State parent) {
            this.missionariesLeft = ml;
            this.cannibalsLeft = cl;
            this.boat = b;
            this.missionariesRight = 3 - ml;
            this.cannibalsRight = 3 - cl;
            this.parent = parent;
            this.g = parent == null ? 0 : parent.g + 1;
            this.h = heuristic();
            this.isSolved = false;
            this.children = new ArrayList<>();
            this.bestChild = null;
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
            return missionariesLeft + cannibalsLeft;
        }

        public int getF() {
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

        public void updateBestChild() {
            if (children.isEmpty()) return;

            children.sort(Comparator.comparingInt(State::getF));
            this.bestChild = children.get(0);
            this.h = bestChild.getF() - this.g; // update heuristic
        }
    }

    public static void AOStar(State initialState) {
        Set<State> openList = new HashSet<>();
        Set<State> closedList = new HashSet<>();

        initialState.updateBestChild();
        openList.add(initialState);

        while (!openList.isEmpty()) {
            State current = getMostPromisingState(openList);
            openList.remove(current);
            closedList.add(current);

            if (current.isGoal()) {
                backtrackSolution(current);
                return;
            }

            for (State successor : current.generateSuccessors()) {
                if (!closedList.contains(successor)) {
                    openList.add(successor);
                    current.children.add(successor);
                }
            }

            current.updateBestChild();

            if (current.bestChild != null) {
                openList.add(current.bestChild);
            }
        }
        System.out.println("No solution found.");
    }

    private static State getMostPromisingState(Set<State> openList) {
        return openList.stream().min(Comparator.comparingInt(State::getF)).orElse(null);
    }

    public static void backtrackSolution(State state) {
        if (state == null) return;
        backtrackSolution(state.parent);
        System.out.println(state.missionariesLeft + "M " + state.cannibalsLeft + "C ~~~~ " 
                            + (3 - state.missionariesLeft) + "M " + (3 - state.cannibalsLeft) + "C");
    }

    public static void main(String[] args) {
        State initialState = new State(3, 3, 1, null);
        AOStar(initialState);
    }
}
