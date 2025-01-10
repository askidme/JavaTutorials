package conc.parstream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> list = List.of(1,2,3,4,5,6);
        Stream<Integer> parallelStream = list.parallelStream();
        parallelStream.forEach(System.out::println);
    }
}
