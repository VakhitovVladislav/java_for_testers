package ru.stqa.geometry.figures;

public class Triangle {

    private double legOne;
    private double legTwo;
    private double hypotenuse;

    public Triangle(double legOne, double legTwo, double hypotenuse){
        this.legOne = legOne;
        this.legTwo = legTwo;
        this.hypotenuse = hypotenuse;
    }

    public double trianglePerimeter(){
        return this.legOne + this.legTwo + this.hypotenuse;
    }

    public double triangleArea(){
        double p = trianglePerimeter()/2;
        return Math.sqrt(p * (p - this.legOne) * (p - this.legTwo) * (p - this.hypotenuse));
    }

}

