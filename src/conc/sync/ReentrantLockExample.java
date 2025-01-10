package conc.sync;

import conc.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ReentrantLockExample {
    public static void main(String[] args) {
        Counter counter = new Counter();
        try (ExecutorService service = Executors.newFixedThreadPool(2)) {

            service.submit(() ->
                    IntStream.range(0, 1000).forEach(i -> counter.increment()));

            service.submit(() ->
                    IntStream.range(0, 1000).forEach(i -> counter.increment()));
        }

        System.out.println("Final Count: " + counter.getCount());
    }
}

class Counter {
    private int count;
    private final Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}

