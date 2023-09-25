package ru.stqa.geometry.figures;

import java.util.Objects;

public record Rectangle(
        double a,
        double b) {
    public Rectangle{
        if (a < 0 || b < 0){
            throw new IllegalArgumentException("Rectangle side should be non-negative");
        }
    }

    public static void printRectangleArea(double a, double b) {
        var text = String.format("Площадь прямоугольника  со сторонами %f +%f = %f", a, b, rectangleArea(a, b));
        System.out.println(text);
    }

    public static double rectangleArea(double a, double b) {

        return a * b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.a, a) == 0 && Double.compare(rectangle.b, b) == 0
                || Double.compare(rectangle.a, b) == 0 && Double.compare(rectangle.b, a) == 0;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
