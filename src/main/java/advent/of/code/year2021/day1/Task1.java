package advent.of.code.year2021.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task1 {
    public static void main(String[] args) throws IOException {
        var data = Files.lines(Path.of("src/main/resources/day1.txt"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println(countIncreasingNeighbours(data));
        System.out.println(countIncreasingSumOfThreeNeighbours(data));

    }

    public static long countIncreasingNeighbours(List<Integer> numbers) {
        return IntStream.rangeClosed(0, numbers.size() - 2)
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(numbers.get(i), numbers.get(i + 1)))
                .filter(pair -> pair.getKey() < pair.getValue())
                .count();
    }

    public static long countIncreasingSumOfThreeNeighbours(List<Integer> numbers) {
        var sumOfThreeNeighbours = IntStream.rangeClosed(0, numbers.size() - 3)
                .map(i -> numbers.get(i) + numbers.get(i + 1) + numbers.get(i + 2))
                .boxed()
                .collect(Collectors.toList());
        return countIncreasingNeighbours(sumOfThreeNeighbours);

    }
}
