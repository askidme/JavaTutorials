package conc.executor;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class CheckResultsFuture {
    private static int counter;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            Future<?> result = executorService.submit(() -> IntStream.range(0, 500).forEach(i -> counter++));
            System.out.println("result.isDone(): " + result.isDone());
            result.get(10, TimeUnit.SECONDS);
            System.out.println("Reached! " + counter);
            System.out.println("result.isDone(): " + result.isDone());
        }catch (TimeoutException e) {
            System.out.println("Not reached in time");
        }
    }
}
