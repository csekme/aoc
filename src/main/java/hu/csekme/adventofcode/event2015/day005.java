package hu.csekme.adventofcode.event2015;

import hu.csekme.adventofcode.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class day005 implements CommandLineRunner {

    final FileService fileService;

    boolean hasDuplicates(String line) {
        char[] chars = line.toCharArray();
        for (int i=0; i<chars.length; i++) {
            if (i>0 && chars[i]==chars[i-1]) {
                return true;
            }
        }
        return false;
    }

    boolean hasDuplicatesOfTwo(String line) {
        char[] chars = line.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            char first = chars[i - 1];
            char second = chars[i];
            for (int j = 1; j < chars.length; j++) {
                if (first == chars[j - 1] && chars[j] == second && (j<i-1 || j>i+1)) {
                    return true;
                }
            }

        }
        return false;
    }

    boolean hasDuplicatesWithBrake(String line) {
        char[] chars = line.toCharArray();
        for (int i = 2; i < chars.length; i++) {
            char first = chars[i - 2];
            char second = chars[i];
            if (first == second) {
                return true;
            }

        }
        return false;
    }


    int countOfM(String line) {
        int count = 0;
        for (char c : line.toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void run(String... args) {
        Optional<Collection<String>> linesOption = fileService.readFileLines("2015/input-day-05.txt");
        if (linesOption.isPresent()) {
            List<String> forbiddens = Arrays.asList("ab", "cd", "pq", "xy");
            int nice = 0;
            for (String line : linesOption.get()) {

                if (hasDuplicates(line) && countOfM(line) > 2 && forbiddens.stream().noneMatch(line::contains)) {
                    nice++;
                }
            }
            log.info(nice + " nice lines");
            nice = 0;
            for (String line : linesOption.get()) {

                if (hasDuplicatesOfTwo(line) && hasDuplicatesWithBrake(line)) {
                    nice++;
                }
            }
            log.info(nice + " nice lines");
        }
    }
}
