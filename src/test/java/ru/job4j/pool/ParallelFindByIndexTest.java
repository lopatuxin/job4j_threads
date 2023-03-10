package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.pool.ParallelFindByIndex.recursiveFind;

class ParallelFindByIndexTest {
    @Test
    void whenFindInteger() {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer index = 5;
        Integer rsl = recursiveFind(array, index);
        assertThat(rsl).isEqualTo(4);
    }

    @Test
    void whenFindString() {
        String[] array = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};
        String index = "f";
        Integer rsl = recursiveFind(array, index);
        assertThat(rsl).isEqualTo(5);
    }

    @Test
    void whenRecursiveFind() {
        Object[] array = new Object[]{"a", "b", "c", "d", "e", "f", 4, 5, 6, 7, 8, 9};
        String index = "e";
        Integer rsl = recursiveFind(array, index);
        assertThat(rsl).isEqualTo(4);
    }

    @Test
    void whenSearchLine() {
        Object[] array = new Object[]{"d", "e", 4, 5, 6, 7, 8};
        String index = "e";
        Integer rsl = recursiveFind(array, index);
        assertThat(rsl).isEqualTo(1);
    }

    @Test
    void whenNotFindElement() {
        String[] array = new String[]{"a", "b", "c", "d", "e"};
        String index = "f";
        Integer rsl = recursiveFind(array, index);
        assertThat(rsl).isEqualTo(-1);
    }

    @Test
    void whenSearchLastElement() {
        Object[] array = new Object[]{"a", "b", "c", "d", "e", "f", 4, 5, 6, 7, 8, 9};
        Integer index = 9;
        Integer rsl = recursiveFind(array, index);
        assertThat(rsl).isEqualTo(11);
    }
}