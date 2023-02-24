package ru.job4j.thread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final int maxSize;

    public SimpleBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (queue.size() == maxSize) {
                wait();
            }
            queue.offer(value);
            notifyAll();
        }
    }

    public synchronized T poll() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                wait();
            }
            T result = queue.poll();
            notifyAll();
            return result;
        }
    }
}
