public class Edge {
    String action;          // action taken
    String state;           // ending state
    double probability;     // probability of taking the transition

    /**
     * Constructor.
     * @param a action
     * @param b state
     * @param c probability
     */
    public Edge(String a, String b, double c) {
        action = a;
        state = b;
        probability = c;
    }

    @Override
    public String toString() {
        return "(" + action + " " + state + " " + probability + ")";
    }


}