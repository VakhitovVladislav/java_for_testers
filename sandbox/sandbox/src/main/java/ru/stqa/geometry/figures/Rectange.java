package ru.stqa.geometry.figures;

public record Rectange(
        double a,
        double b) {



    public static void printRectangleArea(double a, double b) {
        var text = String.format("Площадь прямоугольника  со сторонами %f +%f = %f", a, b, rectangleArea(a, b));
        System.out.println(text);
    }

    public static double rectangleArea(double a, double b) {

        return a * b;
    }
}
