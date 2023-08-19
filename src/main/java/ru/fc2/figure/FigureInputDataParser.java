package ru.fc2.figure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.fc2.figure.exception.InputDataValidationException;
import ru.fc2.figure.shape.*;
import ru.fc2.figure.utils.StringUtils;
import ru.fc2.figure.utils.validation.FigureValidator;
import ru.fc2.figure.utils.validation.ValidatorFactory;

import java.util.Arrays;

public class FigureInputDataParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(FigureInputDataParser.class);
    private static final int MAX_ROWS_ON_INPUT_ARRAY = 2;
    private final String[] inputDataArray;
    private final FigureType figureType;
    private final Double[] parameters;

    public FigureInputDataParser(String inputDataFromFile) {
        checkInputData(inputDataFromFile);
        this.inputDataArray = parseInputDataToArray(inputDataFromFile);
        this.figureType = parseFigureType();
        this.parameters = parseParameters();

    }

    public Figure getFigure() {
        FigureValidator validator = ValidatorFactory.getValidator(figureType);
        if (validator.isValidParameters(parameters)) {
            switch (figureType) {
                case TRIANGLE:
                    return new Triangle(parameters[0], parameters[1], parameters[2]);
                case CIRCLE:
                    return new Circle(parameters[0]);
                case RECTANGLE:
                    double width = Math.min(parameters[0], parameters[1]);
                    double length = Math.max(parameters[1], parameters[0]);
                    return new Rectangle(length, width);
            }
        }
        LOGGER.error(validator.getValidatorMessage());
        throw new InputDataValidationException();
    }

    private String[] parseInputDataToArray(String inputData) {
        String[] array = inputData.trim().split(System.lineSeparator());
        if (array.length == MAX_ROWS_ON_INPUT_ARRAY) {
            return array;
        }
        LOGGER.error("invalid input data, the number of lines in the input file must be equal to two");
        throw new InputDataValidationException();
    }

    private FigureType parseFigureType() {
        String textFigureType = inputDataArray[0];
        try {
            return FigureType.valueOf(textFigureType);
        } catch (IllegalArgumentException e) {
            LOGGER.error("incorrect shape type", e);
            throw e;
        }
    }

    private Double[] parseParameters() {
        Double[] parsedParams = Arrays.stream(inputDataArray[1].split(" "))
                .map(textNumber -> {
                    try {
                        return Double.parseDouble(textNumber);
                    } catch (NumberFormatException e) {
                        LOGGER.error("{} cannot be converted a number", textNumber);
                        throw e;
                    }
                }).toArray(Double[]::new);
        LOGGER.info("input parsed params {}", Arrays.toString(parsedParams));
        return parsedParams;
    }

    private void checkInputData(String inputData) {
        if (StringUtils.checkNotEmpty(inputData)) {
            return;
        }
        LOGGER.error("the input file data is empty");
        throw new InputDataValidationException();
    }
}
