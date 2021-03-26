public class ValueIterator {
    double[] jValues;   // stores J values for previous iteration
    String[] policy;    // stores optimal policy for current iteration

    /**
     * Constructor
     * @param states the number of states in the MDP
     */
    public ValueIterator(int states) {
        jValues = new double[states];
        policy = new String[states];
    }

    /**
     * Calculates 20 iterations of J values. Prints optimal policy after each iteration.
     * @param states array of State objects corresponding to the MDP
     * @param actions the number of possible actions in the MDP
     * @param gamma the discount factor
     */
    public void iterate(State[] states, int actions, double gamma) {
        int iteration = 1;
        while (iteration <= 20) {
            // store new J values here, so you don't overwrite old ones
            double[] newJval = new double[jValues.length];
            // calculate J-value for each state
            for (int i = 0; i < states.length; i++) {
                double[] acts = new double[actions];
                for (int x = 0; x < actions; x++) {
                    acts[x] = Double.NEGATIVE_INFINITY;
                }
                // iterate through each edge
                for (Edge e : states[i].edges) {
                    int aIndex = Integer.parseInt(e.action.substring(1))-1;
                    int sIndex = Integer.parseInt(e.state.substring(1))-1;
                    double calc = e.probability*jValues[sIndex];
                    // set to calc if it's the first value entered
                    if (acts[aIndex] == Double.NEGATIVE_INFINITY) {
                        acts[aIndex] = calc;
                    }
                    // else add to existing array value
                    else {
                        acts[aIndex] += calc;
                    }

                }
                // find the optimal action
                double maxVal = Double.NEGATIVE_INFINITY;
                int maxInd = 0;
                for (int w = 0; w < acts.length; w++) {
                    if (maxVal < acts[w]) {
                        maxVal = acts[w];
                        maxInd = w;
                    }
                }
                // set optimal action
                policy[i] = "a"+(maxInd+1);
                // set new J value
                newJval[i] = states[i].reward + gamma*maxVal;

            }
            // copy new J values to general jValue array
            jValues = newJval;
            System.out.printf("After iteration %d:%n", iteration);
            for (int v = 0; v < jValues.length; v++) {
                System.out.printf("(%s %s %.4f) ", "s"+(v+1), policy[v], jValues[v]);
            }
            System.out.println();
            iteration++;
        }
    }
}