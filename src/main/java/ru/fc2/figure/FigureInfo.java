package ru.fc2.figure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.fc2.figure.commandline.CommandLineParametersInfo;
import ru.fc2.figure.commandline.CommandLineParser;
import ru.fc2.figure.printer.ConsolePrinter;
import ru.fc2.figure.printer.FilePrinter;
import ru.fc2.figure.printer.Printer;
import ru.fc2.figure.shape.Figure;
import ru.fc2.figure.utils.FileUtils;

public final class FigureInfo {
    private final CommandLineParser commandLineParser;
    private static final Logger LOGGER = LoggerFactory.getLogger(FigureInfo.class);

    public FigureInfo(CommandLineParser commandLineParser) {
        this.commandLineParser = commandLineParser;
    }

    public void getFigureInfo() {
        CommandLineParametersInfo commandLineParametersInfo = commandLineParser.getParameters();
        String figureInfoFromFile = FileUtils.readFile(commandLineParametersInfo.getInputFilePath());
        FigureInputDataParser inputDataParser = new FigureInputDataParser(figureInfoFromFile);
        Figure figure = inputDataParser.getFigure();
        Printer printer = getPrinter(commandLineParametersInfo);
        assert printer != null;
        final String printFigureInfo = figure.toString();
        LOGGER.info(printFigureInfo);
        printer.print(printFigureInfo);
    }

    private Printer getPrinter(CommandLineParametersInfo commandLineParametersInfo) {
        switch (commandLineParametersInfo.getPrintMethod()) {
            case CONSOLE:
                return new ConsolePrinter();
            case FILE:
                return new FilePrinter(commandLineParametersInfo.getOutputFilePath());
            default:
                return null;
        }
    }
}
