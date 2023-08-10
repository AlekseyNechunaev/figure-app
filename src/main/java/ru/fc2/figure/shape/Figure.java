package ru.fc2.figure.shape;

import java.util.StringJoiner;

public abstract class Figure {

    protected final String UNITS_OF_MEASUREMENT = "см.";
    private final FigureType figureType;

    public Figure(FigureType figureType) {
        this.figureType = figureType;
    }


    public final String getName() {
        return figureType.getCyrillicName();
    }

    public abstract double getArea();

    public abstract double getPerimeter();

    @Override
    public String toString() {
        final StringJoiner figureInfo = new StringJoiner("\n");
        figureInfo.add(FigureParameterNames.FIGURE_TYPE + ": " + getName());
        figureInfo.add(FigureParameterNames.AREA + ": " + getArea() + " " + UNITS_OF_MEASUREMENT);
        figureInfo.add(FigureParameterNames.PERIMETER + ": " + getPerimeter() + " " + UNITS_OF_MEASUREMENT);
        return figureInfo.toString();
    }
}
