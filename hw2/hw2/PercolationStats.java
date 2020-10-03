package hw2;

public class PercolationStats {
    private int[] sum;
    private int T;
    private int num;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.T = T;
        num = N * N;
        sum = new int[T];
        for (int i = 0; i < T; i++) {
            Percolation test = pf.make(N);
            while (!(test.percolates())) {
                boolean isOpen = true;
                int row = 0;
                int col = 0;
                while (isOpen) {
                    row = edu.princeton.cs.algs4.StdRandom.uniform(N);
                    col = edu.princeton.cs.algs4.StdRandom.uniform(N);
                    isOpen = test.isOpen(row, col);
                }
                test.open(row, col);
            }
            sum[i] = test.numberOfOpenSites();
        }
    }

    public double mean() {
        return edu.princeton.cs.algs4.StdStats.mean(sum) / num;
    }
    public double stddev() {
        return edu.princeton.cs.algs4.StdStats.stddev(sum) / num;
    }
    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(T));
    }
    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(T));
    }
}
