package ru.fc2.figure.utils.validation;

public class RectangleValidator extends FigureValidator {

    private static final int PARAMETERS_COUNT = 2;
    private static final String ERROR_MESSAGE = "The number of parameters should be equal to 2 (length and width), the lengths of each side should be greater than zero, one of the sides should be longer than the other";

    @Override
    public boolean isValidParameters(Double[] parameters) {
        return parametersHasSize(parameters, PARAMETERS_COUNT) && parametersIsGreaterThanZero(parameters)
                && isValidSides(parameters[0], parameters[1]);
    }

    @Override
    public String getValidatorMessage() {
        return ERROR_MESSAGE;
    }

    private boolean isValidSides(double firstSide, double secondSide) {
        return firstSide > secondSide || firstSide < secondSide;
    }
}
