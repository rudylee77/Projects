package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Percolation extends WeightedQuickUnionUF{
    private boolean[][] grid;
    private List<Integer> gridNum;
    private int range;
    private int openSites;
    private WeightedQuickUnionUF weight;

    public Percolation(int N) {
        super(N*N);
        if (N < 0) {
            throw new java.lang.IllegalArgumentException();
        }
        grid = new boolean[N][N];
        gridNum = new ArrayList<Integer>();
        gridNum.add((N * N) + 1);
        gridNum.add((N * N) + 2);
        range = N;
        openSites = 0;
        weight = new WeightedQuickUnionUF((N * N) + 2);
        for (boolean[] row: grid)
            Arrays.fill(row, false);
    }

    private int xyTo1D(int r, int c) {
        return (range * r) + c;
    }

    private boolean connect(int a, int b) {
        if (Math.abs((a / range) - (b / range)) == 1 && (a % range) == (b % range)) {
            return true;
        } else if (Math.abs((a % range) - (b % range)) == 1 && (a / range) == (b / range)) {
            return true;
        }
        return false;
    }

    public void open(int row, int col) {
        if (row >= range || col >= range) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int xy = xyTo1D(row, col);
        grid[row][col] = true;
        gridNum.add(xy);
        if (row == 0) {
            weight.union(range + 1, xy);
        } else if (row == range - 1) {
            weight.union(range + 2, xy);
        }
        for (int i = 2; i < gridNum.size(); i++) {
            int comp = gridNum.get(i);
            if (connect(comp, xy)) {
                weight.union(comp, xy);
            }
        }
        openSites++;
    }

    public boolean isOpen(int row, int col) {
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        int xy = xyTo1D(row, col);
        return weight.connected(range + 1, xy);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return weight.connected(range + 1, range + 2);
    }

    public static void main(String[] args) {

    }
}
