public class Geometry {

    static void pritSquareArea(double side) {
        System.out.println("Площадь квадрата со стороной " + side + " = " + squareArea(side));
    }

    static void printRectangleArea(double a, double b) {
        System.out.println("Площадь прямоугольника  со сторонами " + a + " и " + b + " = " + rectangleArea(a, b));
    }

    private static double squareArea(double side) {
        return side * side;
    }

    private static double rectangleArea(double a, double b) {

        return a * b;
    }

    public static void main(String[] args) {
        pritSquareArea(5.);
        pritSquareArea(6.);
        pritSquareArea(7.);

        printRectangleArea(3.0, 5.0);

    }


}
