package hw2;

public class PercolationStats {
    private int[] sum;
    private int T;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N < 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.T = T;
        sum = new int[T];
        for (int i = 0; i < T; i++) {
            Percolation test = pf.make(N);
            boolean perc = true;
            while (perc) {
                int row = edu.princeton.cs.algs4.StdRandom.uniform(N - 1);
                int col = edu.princeton.cs.algs4.StdRandom.uniform(N - 1);
                test.open(row, col);
                if (test.percolates()) {
                    sum[i] = test.numberOfOpenSites();
                    perc = false;
                }
            }
        }
    }

    public double mean() {
        return edu.princeton.cs.algs4.StdStats.mean(sum);
    }
    public double stddev() {
        return edu.princeton.cs.algs4.StdStats.stddev(sum);
    }
    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(T));
    }
    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(T));
    }
}
