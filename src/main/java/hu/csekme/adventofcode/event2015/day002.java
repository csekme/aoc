package hu.csekme.adventofcode.event2015;

import hu.csekme.adventofcode.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class day002 implements CommandLineRunner {

    final FileService fileService;

    @Override
    public void run(String... args) {

        int l,w,h;
        Optional<Collection<String>> optionalLines = fileService.readFileLines("2015/input-day-02.txt");
        if (optionalLines.isPresent()) {
            Collection<String> fileLines = optionalLines.get();
            int totalPaper = 0;
            int totalRibbon = 0;
            for (String line : fileLines) {
                String[] dimensions = line.split("x");
                l = Integer.parseInt(dimensions[0]);
                w = Integer.parseInt(dimensions[1]);
                h = Integer.parseInt(dimensions[2]);
                var v = l * w * h;
                totalRibbon += (2 * Math.min(Math.min(l + w, w + h), h + l)) + v;
                totalPaper += ((2*l*w) + (2*w*h) + (2*h*l)) + Math.min(Math.min(l*w, w*h), h*l);
            }
            log.info("Total paper: {}", totalPaper);
            log.info("Total ribbon: {}", totalRibbon);
        }

    }
}
