package ru.fc2.figure;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.fc2.figure.commandline.CommandLineParametersInfo;
import ru.fc2.figure.commandline.CommandLineParser;
import ru.fc2.figure.exception.CommandLineValidationException;
import ru.fc2.figure.utils.FileUtils;

public class CommandLineTest {

    private static final String INPUT_FILE_PATH = "src/test/resources/fixture/circle_input.txt";
    private static final String OUTPUT_FILE_PATH = "src/test/resources/fixture/result.txt";

    @Test
    void commandLineParametersIsEmptyErrorTest() {
        Assertions.assertThatThrownBy(() -> new CommandLineParser(new String[0]))
                .isInstanceOf(CommandLineValidationException.class);
    }

    @Test
    void commandLineErrorUnknownPrintMethodErrorTest() {
        String[] parameters = {"-DprintMethod=UNKNOWN", "-DinputFilePath=" + INPUT_FILE_PATH};
        CommandLineParser parser = new CommandLineParser(parameters);
        Assertions.assertThatThrownBy(parser::getParameters).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void commandLineErrorMoreParametersPassedThanPossibleTest() {
        String[] parameters = {"-DprintMethod=FILE",
                "-DinputFilePath=" + INPUT_FILE_PATH,
                "-DsaveFilePath=" + OUTPUT_FILE_PATH,
                "-Dunknownparameter=value"};
        CommandLineParser parser = new CommandLineParser(parameters);
        Assertions.assertThatThrownBy(parser::getParameters).isInstanceOf(CommandLineValidationException.class);
    }

    @Test
    void commandLineErrorPassedParameterDoesNotExistTest() {
        String[] parameters = {"-DprintMethod=FILE",
                "-DinputFilePath=" + INPUT_FILE_PATH,
                "-Dunknown=value" + OUTPUT_FILE_PATH};
        CommandLineParser parser = new CommandLineParser(parameters);
        Assertions.assertThatThrownBy(parser::getParameters).isInstanceOf(CommandLineValidationException.class);
    }

    @Test
    void commandLineErrorIncorrectInputFilePathTest() {
        String[] parameters = {"-DprintMethod=CONSOLE",
                "-DinputFilePath=notPath"};
        CommandLineParser parser = new CommandLineParser(parameters);
        Assertions.assertThatThrownBy(parser::getParameters).isInstanceOf(CommandLineValidationException.class);
    }

    @Test
    void commandLineSuccessParseParametersTest() {
        String[] parameters = {"-DprintMethod=FILE",
                "-DinputFilePath=" + INPUT_FILE_PATH,
                "-DsaveFilePath=" + OUTPUT_FILE_PATH};
        CommandLineParser parser = new CommandLineParser(parameters);
        CommandLineParametersInfo info = parser.getParameters();
        Assertions.assertThat(info.getInputFilePath()).isEqualTo(FileUtils.toAbsoluteAndNormalizePath(INPUT_FILE_PATH));
        Assertions.assertThat(info.getOutputFilePath()).isEqualTo(FileUtils.toAbsoluteAndNormalizePath(OUTPUT_FILE_PATH));
    }
}
