package ru.stqa.geometry.figures;

public class Square {
    public static void pritSquareArea(double side) {
        String text = String.format("Площадь квадрата со стороной %f = %f", side, squareArea(side));
        System.out.println(text);
    }

    private static double squareArea(double side) {
        return side * side;
    }
}
