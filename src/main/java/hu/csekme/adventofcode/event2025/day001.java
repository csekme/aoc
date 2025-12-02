package hu.csekme.adventofcode.event2025;

import hu.csekme.adventofcode.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Component
public class day001 implements CommandLineRunner {

    final FileService fileService;

    static int decrease(int base, int number) {
        return ((base - number) % 100 + 100) % 100;
    }

   static int increase(int base, int number) {
        return (base + number) % 100;
    }

    @Override
    public void run(String... args) {
        Optional<Collection<String>> optionalLines = fileService.readFileLines("2025/input-day-01.txt");
        if (optionalLines.isPresent()) {
            Collection<String> lines = optionalLines.get();
            int countOfZero = 0;
            int countOfZeroPart2 = 0;
            int position = 50;
            for (String line : lines) {
                String dir = line.substring(0, 1);
                int value = Integer.parseInt(line.substring(1));

                if ("R".equals(dir)) {
                    countOfZeroPart2 += (position + value) / 100 - position / 100;
                    position = increase(position, value);
                } else if ("L".equals(dir)) {
                    countOfZeroPart2 += Math.floorDiv(position - 1, 100) - Math.floorDiv(position - value - 1, 100);
                    position = decrease(position, value);
                }

                if (position == 0) {
                    countOfZero++;
                }
            }
            log.info("Part 1: countOfZero = " + countOfZero);
            log.info("Part 2: countOfZeroPart2 = " + countOfZeroPart2);
        }
    }
}
