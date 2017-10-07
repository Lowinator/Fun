public class BoyerMooreMajorityVoteAlgorithm {

    public static void main(String[] args) {

        int[] input = {1, 2, 1, 1, 3, 4, 1, 5, 6, 1, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};

        MajorityHandler handler = new MajorityHandler();

        for (int temp: input) {
            handler.handle(temp);
        }

        if (handler.verifyMajority(input)) {
            System.out.println("Majority of the given sequence: " + handler.getCurrentMajority());
        } else {
            System.out.println("There is no majority in the given sequence.");
        }

        // commit test 2!


    }

    /**
     * Encapsulates the counter, current majority, behaviour upon
     * arrival of new elements and validation of the suspected majority.
     */
    private static class MajorityHandler {

        private int counter;
        private int currentMajority;

        MajorityHandler() {
            counter = 0;
        }

        /**
         * Handles new element from the stream.
         * @param element New element from the stream.
         */
        void handle(int element) {

            if (counter == 0) {
                currentMajority = element;
                counter = 1;
            } else if (currentMajority == element) {
                counter++;
            } else {
                counter--;
            }

        }

        int getCurrentMajority() {
            return currentMajority;
        }

        /**
         * Iterate through the given array and verifies if currentMajority is a majority.
         * @param arr The array we are inspecting.
         * @return True if currentMajority is a majority, otherwise false.
         */
        boolean verifyMajority(int[] arr) {
            int counter = 0;

            for (int temp: arr) {
                if (temp == currentMajority) {
                    counter++;
                }
            }

            if (counter > arr.length/2) {
                return true;
            }
            else {
                return false;
            }

        }
    }

}
