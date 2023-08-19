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
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public String getInfo() {
        final String generalFigureInfo = super.getInfo();
        final StringJoiner joiner = new StringJoiner(LINE_SEPARATOR);
        joiner.add(generalFigureInfo);
        joiner.add(FigureParameterNames.DIAGONAL_LENGTH + ": " + getDiagonalLength() + " " + UNITS_OF_MEASUREMENT);
        joiner.add(FigureParameterNames.LENGTH + ": " + length + " " + UNITS_OF_MEASUREMENT);
        joiner.add(FigureParameterNames.WIDTH + ": " + width + " " + UNITS_OF_MEASUREMENT);
        return joiner.toString();
    }
}
