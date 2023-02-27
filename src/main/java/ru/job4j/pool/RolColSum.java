package ru.job4j.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Sums sums = (Sums) o;

            if (rowSum != sums.rowSum) return false;
            return colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            int result = rowSum;
            result = 31 * result + colSum;
            return result;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] result = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = new Sums();
            for (int j = 0; j < matrix.length; j++) {
                result[i].setColSum(matrix[i][j] + result[i].getColSum());
                result[i].setRowSum(matrix[i][j] + result[i].getRowSum());
            }
        }
        return result;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] result = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> future = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            future.put(i, getTask(matrix, i));
        }
        for (Integer key : future.keySet()) {
            result[key] = future.get(key).get();
        }
        return result;
    }

    private static CompletableFuture<Sums> getTask(int[][] matrix, int start) {
        return CompletableFuture.supplyAsync(() -> {
            Sums sums = new Sums();
            for (int i = start; i < matrix.length; i++) {
                sums.setRowSum(matrix[start][i] + sums.getRowSum());
                sums.setColSum(matrix[i][start] + sums.getColSum());
            }
            return sums;
        });
    }
}
