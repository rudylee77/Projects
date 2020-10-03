package hw2;

public class PercolationStats {
    private double[] sum;
    private int T;
    private double mean;
    private double stddev;
    private double confidenceLow;
    private double confidenceHigh;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.T = T;
        sum = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation test = pf.make(N);
            int count = 0;
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
                count++;
            }
            sum[i] = count / (N * N);
        }
        mean = edu.princeton.cs.algs4.StdStats.mean(sum);
        stddev = edu.princeton.cs.algs4.StdStats.stddev(sum);
        confidenceLow = mean - ((1.96 * stddev) / Math.sqrt(T));
        confidenceHigh = mean + ((1.96 * stddev) / Math.sqrt(T));
    }

    public double mean() {
        return mean;
    }
    public double stddev() {
        return stddev;
    }
    public double confidenceLow() {
        return confidenceLow;
    }
    public double confidenceHigh() {
        return confidenceHigh;
    }
}
