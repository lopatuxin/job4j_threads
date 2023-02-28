package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFindByIndex<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T element;
    private final int to;
    private final int from;

    public ParallelFindByIndex(T[] array, T element, int to, int from) {
        this.array = array;
        this.element = element;
        this.to = to;
        this.from = from;
    }

    @Override
    protected Integer compute() {
        if ((to - from) <= 10) {
            return searchLines();
        }
        int  mid = (from + to) / 2;
        ParallelFindByIndex<T> leftSearch = new ParallelFindByIndex<>(
                array, element, mid, from);
        ParallelFindByIndex<T> rightSearch = new ParallelFindByIndex<>(
                array, element, to, mid + 1);
        leftSearch.fork();
        rightSearch.fork();
        return Math.max(leftSearch.join(), rightSearch.join());
    }

    private int searchLines() {
        int result = -1;
        for (int i = from; i <= to; i++) {
            if (element.equals(array[i])) {
                result = i;
                break;
            }
        }
        return result;
    }

    public static <T> Integer recursiveFind(T[] array, T lineIndex) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelFindByIndex<>(
                array, lineIndex, array.length - 1, 0));
    }
}
