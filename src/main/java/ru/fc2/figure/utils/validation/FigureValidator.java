package ru.fc2.figure.utils.validation;

public abstract class FigureValidator {

    public abstract boolean isValidParameters(Double[] parameters);

    public abstract String getValidatorMessage();

    protected final boolean parametersHasSize(Double[] parameters, int exceptedSize) {
        return parameters.length == exceptedSize;
    }

    protected final boolean parametersIsGreaterThanZero(Double[] parameters) {
        for (double parameter : parameters) {
            if (parameter <= 0) {
                return false;
            }
        }
        return true;
    }
}
