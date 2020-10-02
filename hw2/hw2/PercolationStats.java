package hw2;

public class PercolationStats {
    private int grid;
    private int trials;
    private int[] sum;
    private PercolationFactory factory;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N < 0) {
            throw new java.lang.IllegalArgumentException();
        }
        grid = N;
        trials = T;
        factory = pf;
        sum = new int[trials];
    }

    private void simulation() {
        for (int i = 0; i < trials; i++) {
            Percolation test = factory.make(grid);
            boolean perc = true;
            int time = 0;
            while (perc) {
                boolean add = true;
                while (add) {
                    int row = edu.princeton.cs.algs4.StdRandom.uniform(grid - 1);
                    int col = edu.princeton.cs.algs4.StdRandom.uniform(grid - 1);
                    if (!(test.isOpen(row, col))) {
                        test.open(row, col);
                        add = false;
                    }
                }
                time++;
                if (test.percolates()) {
                    sum[i] = time;
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
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
    }
    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
    }
}
