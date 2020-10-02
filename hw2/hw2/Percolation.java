package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.*;

public class Percolation {
    private boolean[][] grid;
    private int top;
    private int N;
    private int range;
    private int openSites;
    private List<Integer> lastrow;
    private WeightedQuickUnionUF weight;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        grid = new boolean[N][N];
        top = (N * N);
        this.N = N;
        range = (N * N) + 1;
        openSites = 0;
        weight = new WeightedQuickUnionUF((N * N) + 3);
        for (boolean[] row: grid) {
            Arrays.fill(row, false);
        }
        lastrow = new ArrayList<>();
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
        if (weight.connected(xy, top) && lastrow.size() > 0) {
            for (int i = 0; i < lastrow.size(); i++) {
                int last = lastrow.get(i);
                if (weight.connected(top, last)) {
                    weight.union(last, range);
                    lastrow.remove(i);
                }
            }
        }
    }

    public void open(int row, int col) {
        if (row >= N || col >= N || row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (!(grid[row][col])) {
            grid[row][col] = true;
            int xy = xyTo1D(row, col);
            if (row == 0) {
                weight.union(xy, top);
            }
            if (row == N - 1) {
                if (weight.connected(top, xy)) {
                    weight.union(xy, range);
                } else {
                    lastrow.add(xy);
                }
            }
            combine(row, col);
            openSites++;
        }
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
