package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;


public class Percolation {
    private boolean[][] grid;
    private int top = 0;
    private int N;
    private int range;
    private int openSites;
    private WeightedQuickUnionUF weight;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        grid = new boolean[N][N];
        this.N = N;
        range = (N * N);
        openSites = 0;
        weight = new WeightedQuickUnionUF((N * N) + 1);
        for (boolean[] row: grid) {
            Arrays.fill(row, false);
        }
    }

    private int xyTo1D(int r, int c) {
        return (N * r) + c;
    }

    private void combine(int r, int c) {
        int xy = xyTo1D(r, c);
        if (r > 0 && isOpen(r - 1, c)) {
            weight.union(xy, xyTo1D(r - 1, c));
        }
        if (c > 0 && isOpen(r, c - 1)) {
            weight.union(xy, xyTo1D(r, c - 1));
        }
        if (r < N - 1 && isOpen(r + 1, c)) {
            weight.union(xy, xyTo1D(r + 1, c));
        }
        if (c < N - 1 && isOpen(r, c + 1)) {
            weight.union(xy, xyTo1D(r, c + 1));
        }
    }

    private boolean backwash(int c) {
        if (isFull(N - 2, c)) {
            return true;
        }
        return false;
    }

    public void open(int row, int col) {
        if (row >= N || col >= N || row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        grid[row][col] = true;
        int xy = xyTo1D(row, col);
        if (row == 0) {
            weight.union(xy, top);
        } else if (row == N - 1) {
            if (backwash(col)) {
                weight.union(xy, range);
            }
        }
        combine(row, col);
        openSites++;
    }

    public boolean isOpen(int row, int col) {
        if (row >= N || col >= N || row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row >= N || col >= N || row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        } else if (openSites == 0) {
            return false;
        }
        return weight.connected(top, xyTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return weight.connected(top, range);
    }

    public static void main(String[] args) {

    }
}
