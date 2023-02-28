package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.*;

class RolColSumTest {

    @Test
    public void whenSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Sums expected = new Sums();
        expected.setColSum(24);
        expected.setRowSum(24);
        assertThat(RolColSum.sum(matrix)[2]).isEqualTo(expected);
    }

    @Test
    public void whenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Sums expected = new Sums();
        expected.setColSum(13);
        expected.setRowSum(11);
        assertThat(RolColSum.asyncSum(matrix)[1]).isEqualTo(expected);
    }
}