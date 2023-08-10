package ru.fc2.figure.shape;

import java.util.StringJoiner;

public abstract class Figure {

    protected static final String UNITS_OF_MEASUREMENT = "см.";

    protected static final String LINE_SEPARATOR = System.lineSeparator();
    private final FigureType figureType;

    public Figure(FigureType figureType) {
        this.figureType = figureType;
    }


    public final String getName() {
        return figureType.getCyrillicName();
    }

    public abstract double getArea();

    public abstract double getPerimeter();

    public String getInfo() {
        final StringJoiner figureInfo = new StringJoiner(LINE_SEPARATOR);
        figureInfo.add(FigureParameterNames.FIGURE_TYPE + ": " + getName());
        figureInfo.add(FigureParameterNames.AREA + ": " + getArea() + " " + UNITS_OF_MEASUREMENT);
        figureInfo.add(FigureParameterNames.PERIMETER + ": " + getPerimeter() + " " + UNITS_OF_MEASUREMENT);
        return figureInfo.toString();
    }
}
