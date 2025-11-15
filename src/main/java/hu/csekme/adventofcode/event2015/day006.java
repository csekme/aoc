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
@Component
public class day006 implements CommandLineRunner {

    final FileService fileService;

    enum DO {
        ON,
        OFF,
        TOGGLE
    }

    void process(int[][] map, DO d, int fx, int fy, int tx, int ty) {
       log.info("DO {}, fromX={}, toX={}", d, fx, tx);
        for (int x = fx-1; x < tx; x++) {
            for (int y = fy-1; y < ty; y++) {
                switch (d) {
                    case ON:
                        map[x][y] = 1;
                        break;
                    case OFF:
                        map[x][y] = 0;
                        break;
                    case TOGGLE:
                        map[x][y] = map[x][y]==1?0:1;
                        break;
                }
            }
        }
    }

    void processv2(int[][] map, DO d, int fx, int fy, int tx, int ty) {
        log.info("DO {}, fromX={}, toX={}", d, fx, tx);
        for (int x = fx-1; x < tx; x++) {
            for (int y = fy-1; y < ty; y++) {
                switch (d) {
                    case ON:
                        map[x][y] += 1;
                        break;
                    case OFF:
                        map[x][y] = map[x][y]>0?map[x][y]-1:0;
                        break;
                    case TOGGLE:
                        map[x][y] += 2;
                        break;
                }
            }
        }
    }
    @Override
    public void run(String... args) {
        int[][] map = new int[1000][1000];
        Optional<Collection<String>> optionalLines = fileService.readFileLines("2015/input-day-06.txt");
        if (optionalLines.isPresent()) {
            Collection<String> lines = optionalLines.get();
            for (String line : lines) {
                String[] split = line.split(" ");
                if (line.startsWith("toggle")) {
                    String[] from = split[1].split(",");
                    String[] to = split[3].split(",");
                    processv2(map, DO.TOGGLE, Integer.parseInt(from[0]), Integer.parseInt(from[1]), Integer.parseInt(to[0]), Integer.parseInt(to[1]));
                } else if (line.startsWith("turn on")) {
                    String[] from = split[2].split(",");
                    String[] to = split[4].split(",");
                    processv2(map, DO.ON, Integer.parseInt(from[0]), Integer.parseInt(from[1]), Integer.parseInt(to[0]), Integer.parseInt(to[1]));
                } else if (line.startsWith("turn off")) {
                    String[] from = split[2].split(",");
                    String[] to = split[4].split(",");
                    processv2(map, DO.OFF, Integer.parseInt(from[0]), Integer.parseInt(from[1]), Integer.parseInt(to[0]), Integer.parseInt(to[1]));
                }
            }
            int count = 0;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    count+=map[i][j]; //task 2
                   // task 1
                    // if (map[i][j] == 1) {
                   //     count++;
                   // }
                }
            }
            log.info(count + " bulb is lighting");
        }

    }
}
