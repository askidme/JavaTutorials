package conc.parstream;

import java.util.stream.Stream;

public class ParallelExample {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        System.out.println("is the stream parallel: "+stream.isParallel());
        System.out.println();
        Stream<Integer> parallelStream = Stream.of(1, 2, 3, 4, 5).parallel();
        System.out.println("is the parallel stream: "+parallelStream.isParallel());
        parallelStream.forEach(System.out::println);
    }
}
