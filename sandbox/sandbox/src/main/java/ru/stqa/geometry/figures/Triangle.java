package ru.stqa.geometry.figures;

public record Triangle(double legOne, double legTwo, double hypotenuse) {
    public Triangle {
        if ((legOne < 0 || legTwo < 0 || hypotenuse < 0) || (legOne + legTwo < hypotenuse ||
                legOne + hypotenuse < legTwo || legTwo + hypotenuse < legOne)) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }

}
    public double trianglePerimeter() {
        return this.legOne + this.legTwo + this.hypotenuse;
    }

    public double triangleArea() {
        double p = trianglePerimeter() / 2;
        return Math.sqrt(p * (p - this.legOne) * (p - this.legTwo) * (p - this.hypotenuse));
    }
}

