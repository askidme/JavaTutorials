package conc.executor;

import java.util.concurrent.*;

public class Schedule {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        System.out.println(Runtime.getRuntime().availableProcessors());
        try(ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor()) {
            Runnable task = () -> System.out.println("Hello World");
            ScheduledFuture<Integer> result = service.schedule(() -> 50, 10, TimeUnit.SECONDS);
            ScheduledFuture<?> result2 = service.scheduleWithFixedDelay(task, 2, 1, TimeUnit.SECONDS);
            System.out.println(result.get());
            result2.get();
        }
    }
}
