package conc.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ZooInfo {
    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            System.out.println("begin");
            service.execute(() -> System.out.println("Printing zoo inventory"));
            service.execute(() -> IntStream.rangeClosed(0, 3).forEach(i -> System.out.println("Printing record: " + i)));
            service.execute(() -> System.out.println("Printing zoo inventory"));
            System.out.println("end");

        }finally {
            if(service != null){
//                service.shutdownNow();
                service.shutdown();
            }
        }
    }
}
