package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.*;

public class Percolation {
    private boolean[][] grid;
    private int top;
    private int N;
    private int range;
    private int openSites;
    private WeightedQuickUnionUF weight;
    private WeightedQuickUnionUF weight2;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        grid = new boolean[N][N];
        top = (N * N);
        this.N = N;
        range = (N * N) + 1;
        openSites = 0;
        weight = new WeightedQuickUnionUF((N * N) + 2);
        weight2 = new WeightedQuickUnionUF((N * N) + 1);
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
            int comp = xyTo1D(r - 1, c);
            weight.union(xy, comp);
            weight2.union(xy, comp);
        }
        if (c > 0 && isOpen(r, c - 1)) {
            int comp2 = xyTo1D(r, c - 1);
            weight.union(xy, comp2);
            weight2.union(xy, comp2);
        }
        if (r < N - 1 && isOpen(r + 1, c)) {
            int comp3 = xyTo1D(r + 1, c);
            weight.union(xy, comp3);
            weight2.union(xy, comp3);
        }
        if (c < N - 1 && isOpen(r, c + 1)) {
            int comp4 = xyTo1D(r, c + 1);
            weight.union(xy, comp4);
            weight2.union(xy, comp4);
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
                weight2.union(xy, top);
            }
            if (row == N - 1) {
                weight.union(xy, range);
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
        return weight.connected(top, xyTo1D(row, col)) && weight2.connected(top, xyTo1D(row, col));
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
