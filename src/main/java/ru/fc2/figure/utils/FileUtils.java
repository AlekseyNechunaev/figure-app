package ru.fc2.figure.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public final class FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    public static String readFile(Path pathToFile) {
        final StringBuilder fileContent = new StringBuilder(150);
        try (InputStream inputStream = Files.newInputStream(pathToFile);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String line = bufferedReader.readLine();
            while (line != null) {
                fileContent.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            LOGGER.error("having problems reading the file", e);
            throw new UncheckedIOException(e);
        }
        final String content = fileContent.toString();
        LOGGER.info("the file has been read successfully, content: {}", content);
        return content;
    }

    public static void writeToFile(Path path, String content) {
        try (OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE)) {
            outputStream.write(content.getBytes(StandardCharsets.UTF_8));
            LOGGER.info("writing to the file was completed successfully, content: {}", content);
        } catch (IOException e) {
            LOGGER.error("having problems writing to the file", e);
            throw new UncheckedIOException(e);
        }
    }

    public static Path toAbsoluteAndNormalizePath(String path) {
        try {
            return Path.of(path).toAbsolutePath().normalize();
        } catch (InvalidPathException e) {
            LOGGER.error("Invalid path to file", e);
            throw e;
        }
    }

    public static boolean isExistFile(Path path) {
        return Files.exists(path);
    }

    private FileUtils() {

    }
}
