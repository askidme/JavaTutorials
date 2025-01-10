package conc.parstream;

import java.util.stream.LongStream;

public class StreamPerformance {
    public static void main(String[] args) {
        long start = System.nanoTime();
        long serialSum = LongStream.range(1, 10_000_000_000L).sum();
        long end = System.nanoTime();
        System.out.println("Serial sum = " + serialSum +
                " in " + ((end - start)/1_000_000) + " ms");
    }
}
