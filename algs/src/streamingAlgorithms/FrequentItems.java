package streamingAlgorithms;

import  java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class FrequentItems {

    // TODO test it, generalize it for the cash-register model

    public static void main(String[] args) {

    }

    // time series (vanilla) model
    Integer[] determineFrequentItems(int[] stream, double epsilon) {

        Map<Integer, Integer> candidates = new HashMap<>();

        for (int curr : stream) {

            if (candidates.containsKey(curr)) {
                candidates.put(curr, candidates.get(curr)+1);
            } else {

                // insert new element
                candidates.put(curr, 1);

                if (candidates.size() >= 1/epsilon) {

                    // decrement all counters
                    candidates.replaceAll((k, v) -> v - 1);

                    // remove counters which are equal to 0
                    for(Iterator<Map.Entry<Integer, Integer>> it = candidates.entrySet().iterator(); it.hasNext(); ) {
                        Map.Entry<Integer, Integer> entry = it.next();
                        if(entry.getKey() == 0) {
                            it.remove();
                        }
                    }
                }
            }
        }

        // returns the superset of epsilon-frequent items
        return candidates.keySet().toArray(new Integer[candidates.size()]);

    }

    // cash register model
    // TODO figure out how to manage the
    Integer[] determineFrequentItemsCRM(int[] stream, double epsilon) {

        Map<Integer, Integer> candidates = new HashMap<>();

        for (int curr : stream) {

            if (candidates.containsKey(curr)) {
                candidates.put(curr, candidates.get(curr)+c);
            } else {

                // insert new element
                candidates.put(curr, 1);

                // get candidate with minimal c
                minCandidate = candidates.getMin();

                if (candidates.size() >= 1/epsilon) {

                    // decrement all counters
                    candidates.replaceAll((k, v) -> v - minCandidate);

                    // remove counters which are equal to 0
                    for(Iterator<Map.Entry<Integer, Integer>> it = candidates.entrySet().iterator(); it.hasNext(); ) {
                        Map.Entry<Integer, Integer> entry = it.next();
                        if(entry.getKey() == 0) {
                            it.remove();
                        }
                    }
                }
            }
        }

        // returns the superset of epsilon-frequent items
        return candidates.keySet().toArray(new Integer[candidates.size()]);

    }
}
