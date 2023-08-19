package ru.fc2.figure.utils.validation;

import ru.fc2.figure.shape.FigureType;

public final class ValidatorFactory {

    public static FigureValidator getValidator(FigureType figureType) {
        switch (figureType) {
            case RECTANGLE:
                return new RectangleValidator();
            case CIRCLE:
                return new CircleValidator();
            case TRIANGLE:
                return new TriangleValidator();
            default:
                throw new IllegalArgumentException();
        }
    }
    private ValidatorFactory() {

    }
}
