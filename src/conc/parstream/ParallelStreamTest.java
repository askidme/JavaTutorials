package conc.parstream;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class ParallelStreamTest {
    public static void main(String[] args) {
        long start = System.nanoTime();
        long parallelSum = LongStream.range(1, 10_000_000_000L)
                .parallel().sum();
        long end = System.nanoTime();
        System.out.println("Parallel sum = " + parallelSum +
                " in " + ((end - start)/1_000_000) + " ms");
    }
}
