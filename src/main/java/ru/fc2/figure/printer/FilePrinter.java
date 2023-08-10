package ru.fc2.figure.printer;

import ru.fc2.figure.utils.FileUtils;

import java.nio.file.Path;

public class FilePrinter implements Printer {

    private final Path pathToFile;

    public FilePrinter(Path pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public void print(String content) {
        FileUtils.writeToFile(pathToFile, content);
    }
}
