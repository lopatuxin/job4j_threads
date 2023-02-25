package ru.job4j.thread;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CASCountTest {

    @Test
    void when3IncrementThen3Get() throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread first = new Thread(
                () -> {
                    casCount.increment();
                    casCount.increment();
                }
        );
        Thread second = new Thread(
                () -> {
                    casCount.increment();
                }
        );
        first.start();
        second.start();
        Thread.sleep(3000);
        assertThat(casCount.get()).isEqualTo(3);
    }
}