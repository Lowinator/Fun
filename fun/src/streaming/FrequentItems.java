package streaming;

import java.util.Collections;
import  java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/** Given an input stream of length m over the universe [n], we call an item j \in [n]
 * eps-frequent iff. freq[j] > eps*m , where freq[j] is the frequency (number of times it's
 * contained in the stream) of item j.
 *
 * Given a stream and an eps, the presented algorithm computes an approximation of the
 * set of eps-frequent items. In other words, the returned set of items will contain
 * all eps-frequent items but it might also contain items which aren't eps-frequent.
 *
 * The algorithm uses O((1/eps) * log(n+m) bits (units) of storage. As already mentioned the solution
 * is always an overestimate, all eps-frequent items are guaranteed to be returned.
 *
 * More information:
 * http://dimacs.rutgers.edu/~graham/pubs/papers/freq.pdf
 */

public class FrequentItems {

    public static void main(String[] args) {

    }

    /**
     *The algorithm is based on the following idea. Suppose that, given a sequence of numbers,
     we repeatedly delete any pair of adjacent and distinct numbers, until either all remaining
     numbers are equal or the sequence has become empty. Then the remaining number, if any,
     is the only candidate for a majority item (freqency of >0.5, makes up more than 50% of the stream).
     Indeed, for each item j that we delete we also
     delete another item (not equal to j), and since the number of occurrences of a majority item
     is larger than the total number of occurrences of other items, it is impossible to delete all
     occurrences of a majority item. This way we can compute a candidate for the majority item for a given stream,
     but we cannot be certain if its actually a majority item (at least not with one pass, if we had another pass
     then we obviously could check if candidate indeed is a majority item or not).

     The following algorithm generalizes the idea above in order to compute a superset of all eps-frequent items.
     We simply maintain a set of items that are candidates to be eps-frequent items. We bound the size of the set, there
     can't be more than 1/eps eps-frequent items (obviously, since for example there can't be more than 1 item whic makes
     up more than 50% (eps=0.5) of the stream). And we update the elements of our candidate set appropriately.

     The algorithm assumes the vanilla (time series) model.

     * @param stream input stream.
     * @param epsilon desired frequency of items.
     * @return superset of all eps-frequent items in the given stream.
     */
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

    /*  The above algorithm generalized to the cash register model.
        Note how we now increment/decrement by the weight of an item instead of 1 as before.
        Also, note how we always decrement by the minimal frequency (when the size of the set gets too large)
        to avoid negative frequencies (its still guaranteed that at least 1 item is removed).
    */
    Integer[] determineFrequentItemsCRM(Item[] stream, double epsilon) {

        // maps an item (element of [n] with a weight (c)) to its observed frequency so far
        Map<Item, Integer> candidates = new HashMap<>();

        for (Item curr : stream) {

            if (candidates.containsKey(curr)) {
                // item is already in the candidate set, update its frequency
                candidates.put(curr, candidates.get(curr)+curr.weight);
            } else {

                // insert new item into candidate set
                candidates.put(curr, curr.weight);

                // check if there are too many candidates, if so then remove at least one
                if (candidates.size() >= 1/epsilon) {

                    // get frequency of item from the candidate with minimal frequency (c)
                    int minCandidate = Collections.min(candidates.values());

                    // decrement all counters by minimal frequency
                    candidates.replaceAll((k, v) -> v - minCandidate);

                    // remove counters which are equal to 0
                    for(Iterator<Map.Entry<Item, Integer>> it = candidates.entrySet().iterator(); it.hasNext(); ) {
                        Map.Entry<Item, Integer> entry = it.next();
                        if(entry.getValue() == 0) {
                            it.remove();
                        }
                    }
                }
            }
        }
        // returns the superset of epsilon-frequent items
        return candidates.keySet().toArray(new Integer[candidates.size()]);
    }

    // represents items in the stream
    private static class Item {
        int ID;
        int weight; // c

        Item(int ID, int weight) {
            this.ID = ID;
            this.weight = weight;
        }
    }
}
