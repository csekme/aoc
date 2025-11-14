package hu.csekme.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileService {

    /**
     * Read file from resources folder
     * @param path path to the file
     * @return Optional containing file content or empty if file not found
     */
    Optional<String> readFile(String path) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path)) {
            Objects.requireNonNull(inputStream, "Resource not found: 2015/input-day-02.txt");
            String content = new String(inputStream.readAllBytes());
            return Optional.of(content);

        } catch (NullPointerException | IOException exception) {
            log.error(exception.getMessage(), exception);
            return Optional.empty();
        }
    }

    /**
     * Read file from resources folder and return lines as a List<String>
     * @param path path to the file
     * @return Optional containing list of lines or empty if file not found / error
     */
    Optional<List<String>> readFileLines(String path) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path)) {
            Objects.requireNonNull(inputStream, "Resource not found: " + path);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                List<String> lines = reader.lines().collect(Collectors.toList());
                return Optional.of(lines);
            }
        } catch (NullPointerException | IOException exception) {
            log.error(exception.getMessage(), exception);
            return Optional.empty();
        }
    }

}
