package ru.fc2.figure;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.fc2.figure.exception.InputDataValidationException;
import ru.fc2.figure.shape.Circle;

public class InputDataParserTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    @Test
    void inputContentIsEmptyErrorTest() {
        Assertions.assertThatThrownBy(() -> new FigureInputDataParser(" "))
                .isInstanceOf(InputDataValidationException.class);
    }

    @Test
    void inputUnknownFigureTypeErrorTest() {
        final StringBuilder inputData = new StringBuilder(50);
        inputData.append("CIRCLE")
                .append(LINE_SEPARATOR)
                .append("5")
                .append(LINE_SEPARATOR)
                .append("UNKNOWN TEXT");
        Assertions.assertThatThrownBy(() -> new FigureInputDataParser(inputData.toString()))
                .isInstanceOf(InputDataValidationException.class);
    }

    @Test
    void inputUnknownFigureTypeTest() {
        final StringBuilder inputData = new StringBuilder(50);
        inputData.append("UNKNOWN")
                .append(LINE_SEPARATOR)
                .append("5");
        Assertions.assertThatThrownBy(() -> new FigureInputDataParser(inputData.toString()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void inputIncorrectTypeParametersTest() {
        final StringBuilder inputData = new StringBuilder(50);
        inputData.append("CIRCLE")
                .append(LINE_SEPARATOR)
                .append("TEXT");
        Assertions.assertThatThrownBy(() -> new FigureInputDataParser(inputData.toString()))
                .isInstanceOf(NumberFormatException.class);
    }

    @Test
    void successFigureParseTest() {
        final StringBuilder inputData = new StringBuilder(50);
        inputData.append("CIRCLE")
                .append(LINE_SEPARATOR)
                .append("5");
        FigureInputDataParser figureInputDataParser = new FigureInputDataParser(inputData.toString());
        Assertions.assertThat(figureInputDataParser.getFigure()).isInstanceOf(Circle.class);
    }
}
