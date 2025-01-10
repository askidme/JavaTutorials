package conc.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class SheepManager {

    private int sheepCount;

    private synchronized void incrementAndReport() {
            System.out.print((++sheepCount) + " ");
    }

    public static void main(String[] args) {
        try(ExecutorService service = Executors.newFixedThreadPool(20)){
            SheepManager sheepManager = new SheepManager();
            IntStream.range(0, 10).forEach(i -> service.submit(() -> sheepManager.incrementAndReport()));
        }
    }
}
