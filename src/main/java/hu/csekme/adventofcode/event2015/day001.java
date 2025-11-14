package hu.csekme.adventofcode.event2015;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class day001 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("2015/input-day-01.txt")) {
            assert inputStream != null;
            String content = new String(inputStream.readAllBytes());
            int floor = content.chars()
                    .map(c -> c == '(' ? 1 : c == ')' ? -1 : 0)
                    .sum();

            log.info("Final floor: {}", floor);

            floor = 0;
            for (int i = 1; i <= content.length(); i++) {
                if (content.charAt(i - 1) == '(') {
                    floor++;
                } else if (content.charAt(i - 1) == ')') {
                    floor--;
                }
                if (floor == -1) {
                    log.info("Position of first basement entry: {}", i);
                    break;
                }
            }

        }
    }
}