package ru.fc2.figure.commandline;

import ru.fc2.figure.printer.PrintMethod;

import java.nio.file.Path;

public class CommandLineParametersInfo {
    private final PrintMethod printMethod;
    private final Path inputFilePath;
    private final Path outputFilePath;

    public CommandLineParametersInfo(PrintMethod printMethod, Path inputFilePath, Path outputFilePath) {
        this.printMethod = printMethod;
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    public PrintMethod getPrintMethod() {
        return printMethod;
    }

    public Path getInputFilePath() {
        return inputFilePath;
    }

    public Path getOutputFilePath() {
        return outputFilePath;
    }
}
