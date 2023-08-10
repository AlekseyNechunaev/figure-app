package ru.fc2.figure.shape;

import java.util.StringJoiner;

public class Rectangle extends Figure {

    private final double length;
    private final double width;

    public Rectangle(double length, double width) {
        super(FigureType.RECTANGLE);
        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * length + 2 * width;
    }

    public double getDiagonalLength() {
        return Math.sqrt((Math.pow(length, 2) + Math.pow(width, 2)));
    }

    public double getLength() {
        return Math.max(length, width);
    }

    public double getWidth() {
        return Math.min(length, width);
    }

    @Override
    public String toString() {
        final String generalFigureInfo = super.toString();
        final StringJoiner joiner = new StringJoiner("\n");
        joiner.add(generalFigureInfo);
        joiner.add(FigureParameterNames.DIAGONAL_LENGTH + ": " + getDiagonalLength() + " " + UNITS_OF_MEASUREMENT);
        joiner.add(FigureParameterNames.LENGTH + ": " + getLength() + " " + UNITS_OF_MEASUREMENT);
        joiner.add(FigureParameterNames.WIDTH + ": " + getWidth() + " " + UNITS_OF_MEASUREMENT);
        return joiner.toString();
    }
}
