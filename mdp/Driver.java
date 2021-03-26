import java.io.*;
import java.util.*;

public class Driver {

    public static void main(String[] args){
        // read in training data
        Scanner s = new Scanner(System.in);
        int len;
        System.out.println("Enter the number of states: ");
        len = s.nextInt();
        // parse input file
        System.out.println("Enter state information in the format: state_name reward (action next_state reward) ...");
        System.out.println("for example: s0 10 (a0 s1 0.5) (a1 s1 1) (a2 s2 2)");
        State[] stateInfo = readData(s, len);
        // create value iterator and calculate best policy
        int noOfActions;
        System.out.println("Enter the number of possible actions: ");
        noOfActions = s.nextInt();
        ValueIterator vi = new ValueIterator(len);
        double discountFactor;
        System.out.println("Enter the discount factor: ");
        discountFactor = s.nextDouble();
        vi.iterate(stateInfo, noOfActions, discountFactor);
        s.close();
        
    }

    /**
     * Parses input data.
     * @param in Scanner for input file
     * @param len number of states in the input file
     * @return State array containng all the states and their associated transitions
     * @throws FileNotFoundException if input file is not found
     */
    public static State[] readData(Scanner in, int len){
        State[] rtn = new State[len];
        int pos = 0;
        String line;
        line = in.nextLine();
        do {
            line = in.nextLine();
            // split line on spaces
            String[] splits = line.split(" ");
            // first two values are state values
            State curr = new State(splits[0], Double.parseDouble(splits[1]));
            // split along open parenthesis
            splits = line.split("\\(");
            // starting from 2nd position, each represents a transition
            for (int i = 1; i < splits.length; i++) {
                // split on space, close parenthesis, or close parenthesis and space
                String[] trans = splits[i].split(" |\\) |\\)");
                // the first three elements are transition/edge values
                curr.add(trans[0], trans[1], Double.parseDouble(trans[2]));
            }
            // add the completed state to the array
            rtn[pos] = curr;

            pos++;
        } while (in.hasNextLine() && pos < len);

        return rtn;
    }
}