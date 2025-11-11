package hu.csekme.adventofcode.event2015;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class day001 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("Egyes feladat futva!");

        // Fájl beolvasása
        Path filePath = Paths.get("input-day-01.txt");
        String content = Files.readString(filePath);

        log.info("Fájl tartalma: {}", content);
    }
}
