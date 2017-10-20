package streamingAlgorithms;

/*
Computes the median in O(logn) bits of memory and O(logn) passes through the input array.

*/
public class quickMedian {

    public static void main(String[] args) {

        // expected output: 1000
        // String[] arr = {"0010", "0100", "0111", "1000", "1100", "1111"};

        // expected output: 011
        // String[] arr = {"000", "001", "010", "011", "100", "101"};

        // expected output: 0100
        // String[] arr = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000"};


        // expected output: 0101
        String[] arr = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001"};

        System.out.println("Median is: " + computeMedian(arr));

    }

    /**
     * Computes the median
     * @param arr Array of strings, each element represents a distinct binary number and has the same number of bits (padded with leading zeroes if necessary)
     * @return the median (number of rank ceil((m+1)/2) ) of the array as a string
     */
    static String computeMedian(String[] arr) {

        // rank of the median element
        int m = (int) Math.ceil((arr.length+1)/2.0);

        String bitMask = "";
        int firstBucket = 0;
        int secondBucket = 0;

        boolean a = true;

        while (a) {

            System.out.println("loop: " + bitMask.length() );
            System.out.println("median position: " + m);

            // puts elements which conform to the bitMask into one of two buckets
            for (String curr : arr) {
                if (curr.startsWith(bitMask))
                    if (curr.charAt(bitMask.length()) == '0')
                        firstBucket++;
                    else
                        secondBucket++;
            }

            // decides in which bucket the median is located
            if (firstBucket >= m)
                bitMask = bitMask.concat("0");
            else {
                m -= firstBucket;
                bitMask = bitMask.concat("1");
            }

            firstBucket = secondBucket = 0;

            if (bitMask.length() == arr[0].length())
                return bitMask;
        }


        assert (false);
        return null;
    }

}
