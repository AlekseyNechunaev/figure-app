package ru.fc2.figure;

import ru.fc2.figure.commandline.CommandLineParser;

public class App {

    public static void main(String[] args) {
        CommandLineParser commandLineParser = new CommandLineParser(args);
        FigureInfo figureInfo = new FigureInfo(commandLineParser);
        figureInfo.getFigureInfo();
    }
}
