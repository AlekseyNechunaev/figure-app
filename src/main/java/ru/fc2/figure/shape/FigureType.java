package ru.fc2.figure.shape;

public enum FigureType {
    CIRCLE("Круг"),
    RECTANGLE("Прямоугольник"),
    TRIANGLE("Треугольник");

    private final String cyrillicName;

    FigureType(String cyrillicName) {
        this.cyrillicName = cyrillicName;
    }

    public String getCyrillicName() {
        return cyrillicName;
    }
}
