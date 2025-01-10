package conc.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AddData {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try(ExecutorService service = Executors.newSingleThreadExecutor()) {
            Future<Integer> result = service.submit(() -> 30+11);
            System.out.println(result.get());
        }
    }
}
