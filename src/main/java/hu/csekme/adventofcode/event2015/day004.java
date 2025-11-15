package hu.csekme.adventofcode.event2015;

import hu.csekme.adventofcode.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class day004 implements CommandLineRunner {


    @Override
    public void run(String... args) {
        final String input = "yzbqklnj";
        boolean fiveIsFound = false;
        for (int i = 0; i< Integer.MAX_VALUE; i++) {
            String check = "%s%d".formatted(input, i);
            if (!fiveIsFound && new String(DigestUtils.md5DigestAsHex(check.getBytes()).getBytes(StandardCharsets.UTF_8)).startsWith("00000")) {
                fiveIsFound = true;
                log.info("Start with five zeros: {}", i);

            }
            if (new String(DigestUtils.md5DigestAsHex(check.getBytes()).getBytes(StandardCharsets.UTF_8)).startsWith("000000")) {
                log.info("Start with six zeros: {}", i);
                break;
            }
        }
    }
}
