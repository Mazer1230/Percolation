import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private Percolation p; // creates percolation object
    private double[] mean; // mean of each trial in array
    private int trial; // number of trials run

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException(
                    "Input a valid number for trials or the size of your grid");
        }

        trial = trials;
        mean = new double[trials];

        for (int i = 0; i < trials; i++) {
            p = new Percolation(n);
            while (!p.percolates()) {
                p.open(StdRandom.uniform(n), StdRandom.uniform(n));
            }
            mean[i] = (double) p.numberOfOpenSites() / (double) (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(mean);
    }

    // sample confidence interval
    private double confInt() {
        return (1.96 * stddev()) / Math.sqrt(trial);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(mean);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - confInt();
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + confInt();
    }

    // test client
    public static void main(String[] args) {
        Stopwatch timer = new Stopwatch();
        int size = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats per = new PercolationStats(size, trials);
        System.out.println("mean() = " + per.mean());
        System.out.println("stddev() = " + per.stddev());
        System.out.println("confidenceLow() = " + per.confidenceLow());
        System.out.println("confidenceHigh() = " + per.confidenceHigh());
        System.out.println("elapsed time = " + timer.elapsedTime());
    }
}

// Failed code that was edited out
// private double variance() {
//     return StdStats.var(mean);
// }

