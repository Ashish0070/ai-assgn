import java.util.ArrayList;

public class State {
    String name;                // name of the state
    double reward;              // reward at state
    ArrayList<Edge> edges;      // transitions from current state

    /**
     * Constructor
     * @param n name of the state
     * @param r reward at state
     */
    public State(String n, double r) {
        name = n;
        reward = r;
        edges = new ArrayList<>();
    }

    /**
     * Add a transition to the edges ArrayList
     * @param action action taken
     * @param state ending state
     * @param probability probability of taking the transistion
     */
    public void add(String action, String state, double probability) {
        edges.add(new Edge(action, state, probability));
    }

    @Override
    public String toString() {
        String rtn = name + " " + reward;

        for (Edge e:edges) {
            rtn += " " + e.toString();
        }

        return rtn;
    }
}