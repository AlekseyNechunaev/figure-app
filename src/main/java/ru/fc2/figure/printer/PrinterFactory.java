package ru.fc2.figure.printer;

import ru.fc2.figure.commandline.CommandLineParametersInfo;

public final class PrinterFactory {

    public static Printer getPrinter(CommandLineParametersInfo commandLineParametersInfo) {
        switch (commandLineParametersInfo.getPrintMethod()) {
            case CONSOLE:
                return new ConsolePrinter();
            case FILE:
                return new FilePrinter(commandLineParametersInfo.getOutputFilePath());
            default:
                throw new IllegalArgumentException();
        }
    }

    private PrinterFactory() {

    }
}
