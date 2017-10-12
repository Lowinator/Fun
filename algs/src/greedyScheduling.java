import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

// TODO test it
public class greedyScheduling {

    /**
     * Assigns jobs to machines while minimizing the makespan.
     * This is a (2-1/m) approximation algorithm meaning that the achieved
     * makespan of the returned job-to-machine assignment is (2-1/m)*OPT in the
     * worst case, where OPT denotes the optimal makespan.
     * The approximation ratio is tight: http://www.math.ucsd.edu/~ronspubs/pro_16_albers.pdf
     * @param jobs jobs which will be assigned to machines.
     * @param m number of machines.
     * @return machines with jobs assigned to them.
     */
    static Machine[] greedyScheduling(Job[] jobs, int m) {

  //      Comparator<Machine> comp = new loadComparator();

        PriorityQueue<Machine> queue = new PriorityQueue<Machine>(m, (Machine a, Machine b) -> Double.compare(a.load, b.load));

        // creates m machines
        for (int i = 0; i < m; i++)
            queue.add(new Machine());

        // assigns each job to the machine which during the current iteration has the minimal load
        for (Job curr: jobs) {
            // O(1) peek
            Machine machineWithMinimalLoad = queue.peek();

            // assigns job to machine with minimal load
            machineWithMinimalLoad.assignJob(curr);
        }

        return queue.toArray(new Machine[m]);
    }


    private static class Machine {
        List<Job> jobs;
        double load;

        Machine() {
            jobs = new LinkedList<>();
            load = 0;
        }

        void assignJob(Job j) {
            jobs.add(j);
            load += j.duration;
        }
    }

    private static class Job {
        int ID;
        double duration;
    }


    // RIP Java <8.
   /* // compares machines based on their loads
    private static class loadComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine x, Machine y) {
            return Double.compare(x.load, y.load);
        }
    }
    */
}
