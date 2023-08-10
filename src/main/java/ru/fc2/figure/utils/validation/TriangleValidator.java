package ru.fc2.figure.utils.validation;

public class TriangleValidator extends FigureValidator {

    private static final int PARAMETERS_COUNT = 3;
    private static final String ERROR_MESSAGE = "The number of parameters must be equal to 3 (first side, second side, third side), the lengths of each side must be greater than zero";

    @Override
    public boolean isValidParameters(Double[] parameters) {
        return parametersHasSize(parameters, PARAMETERS_COUNT) && parametersIsGreaterThanZero(parameters);
    }

    @Override
    public String getValidatorMessage() {
        return ERROR_MESSAGE;
    }

}
