package hu.csekme.adventofcode.event2015;

import hu.csekme.adventofcode.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Component
@AllArgsConstructor
public class day003 implements CommandLineRunner {

    final FileService fileService;

    record Position(int x, int y) {}

    @Override
    public void run(String... args) {
        int x=1, y=1;
        Map<Position, Integer> positions = new HashMap<>();
        positions.put(new Position(x,y),1);
        Optional<String> content = fileService.readFile("2015/input-day-03.txt");
        if (content.isPresent()) {
            for (Character c : content.get().toCharArray()) {
                switch (c) {
                    case '^' -> y--;
                    case 'v' -> y++;
                    case '>' -> x++;
                    case '<' -> x--;
                }
                Position position = new Position(x, y);
                if (Objects.isNull(positions.get(position))) {
                    positions.put(position, 1);
                } else {
                    positions.put(position, positions.get(position) + 1);
                }
            }
            log.info("Number of houses: {}", positions.size());
            positions.clear();

            x=1; y=1;
            int rx=1, ry=1, count=1;
            positions.put(new Position(x,y),2);
            for (Character c : content.get().toCharArray()) {
                boolean robot = count%2==0;
                Position position;
                if (robot) {
                    switch (c) {
                        case '^' -> ry--;
                        case 'v' -> ry++;
                        case '>' -> rx++;
                        case '<' -> rx--;
                    }
                    position = new Position(rx, ry);
                } else {
                    switch (c) {
                        case '^' -> y--;
                        case 'v' -> y++;
                        case '>' -> x++;
                        case '<' -> x--;
                    }
                    position = new Position(x, y);
                }

                if (Objects.isNull(positions.get(position))) {
                    positions.put(position, 1);
                } else {
                    positions.put(position, positions.get(position) + 1);
                }
                count++;
            }

            log.info("Number of houses: {}", positions.size());
        }



    }
}
