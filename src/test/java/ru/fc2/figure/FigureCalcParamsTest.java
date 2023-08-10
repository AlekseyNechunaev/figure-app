package ru.fc2.figure;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.fc2.figure.commandline.CommandLineParser;
import ru.fc2.figure.utils.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

public class FigureCalcParamsTest {

    private static final String COMMON_PATH = "src/test/resources/fixture/";
    private static final String INPUT_CIRCLE_FILE_PATH = COMMON_PATH + "circle_input.txt";
    private static final String OUTPUT_CIRCLE_FILE_PATH = COMMON_PATH + "circle_output.txt";
    private static final String INPUT_RECTANGLE_FILE_PATH = COMMON_PATH + "rectangle_input.txt";
    private static final String OUTPUT_RECTANGLE_FILE_PATH = COMMON_PATH + "rectangle_output.txt";
    private static final String INPUT_TRIANGLE_FILE_PATH = COMMON_PATH + "triangle_input.txt";
    private static final String OUTPUT_TRIANGLE_FILE_PATH = COMMON_PATH + "triangle_output.txt";
    private static final String RESULT_FILE_PATH = COMMON_PATH + "result_test.txt";

    @BeforeEach
    void deleteResultFile() throws IOException {
        Files.deleteIfExists(FileUtils.toAbsoluteAndNormalizePath(RESULT_FILE_PATH));
    }

    @Test
    void getCircleInfoTest() {
        String[] parameters = {"-DprintMethod=FILE",
                "-DinputFilePath=" + INPUT_CIRCLE_FILE_PATH,
                "-DsaveFilePath=" + RESULT_FILE_PATH};
        FigureInfo figureInfo = new FigureInfo(new CommandLineParser(parameters));
        figureInfo.getFigureInfo();
        String exceptedResult = readFile(OUTPUT_CIRCLE_FILE_PATH);
        String result = readFile(RESULT_FILE_PATH);
        Assertions.assertThat(result).isEqualTo(exceptedResult);
    }

    @Test
    void getRectangleInfoTest() {
        String[] parameters = {"-DprintMethod=FILE",
                "-DinputFilePath=" + INPUT_RECTANGLE_FILE_PATH,
                "-DsaveFilePath=" + RESULT_FILE_PATH};
        FigureInfo figureInfo = new FigureInfo(new CommandLineParser(parameters));
        figureInfo.getFigureInfo();
        String exceptedResult = readFile(OUTPUT_RECTANGLE_FILE_PATH);
        String result = readFile(RESULT_FILE_PATH);
        Assertions.assertThat(result).isEqualTo(exceptedResult);
    }

    @Test
    void getTriangleInfoTest() {
        String[] parameters = {"-DprintMethod=FILE",
                "-DinputFilePath=" + INPUT_TRIANGLE_FILE_PATH,
                "-DsaveFilePath=" + RESULT_FILE_PATH};
        FigureInfo figureInfo = new FigureInfo(new CommandLineParser(parameters));
        figureInfo.getFigureInfo();
        String exceptedResult = readFile(OUTPUT_TRIANGLE_FILE_PATH);
        String result = readFile(RESULT_FILE_PATH);
        Assertions.assertThat(result).isEqualTo(exceptedResult);
    }

    @Test
    void getFigureInfoOnConsoleTest() throws IOException {
        final PrintStream standardOut = System.out;
        try (ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(outputStreamCaptor));
            String[] parameters = {"-DprintMethod=CONSOLE",
                    "-DinputFilePath=" + INPUT_TRIANGLE_FILE_PATH};
            FigureInfo figureInfo = new FigureInfo(new CommandLineParser(parameters));
            figureInfo.getFigureInfo();
            String exceptedResult = readFile(OUTPUT_TRIANGLE_FILE_PATH);
            Assertions.assertThat(outputStreamCaptor.toString()).contains(exceptedResult);
        } finally {
            System.setOut(new PrintStream(standardOut));
        }
    }

    private String readFile(String path) {
        return FileUtils.readFile(FileUtils.toAbsoluteAndNormalizePath(path));
    }
}
