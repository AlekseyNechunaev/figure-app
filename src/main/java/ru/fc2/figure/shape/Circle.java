package ru.fc2.figure.shape;

import java.util.StringJoiner;

public class Circle extends Figure {

    private final double radius;

    public Circle(double radius) {
        super(FigureType.CIRCLE);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public double getDiameter() {
        return radius * 2;
    }

    @Override
    public String toString() {
        final String generalFigureInfo = super.toString();
        final StringJoiner joiner = new StringJoiner("\n");
        joiner.add(generalFigureInfo);
        joiner.add(FigureParameterNames.DIAMETER + ": " + getDiameter() + " " + UNITS_OF_MEASUREMENT);
        joiner.add(FigureParameterNames.RADIUS + ": " + radius + " " + UNITS_OF_MEASUREMENT);
        return joiner.toString();
    }
}
