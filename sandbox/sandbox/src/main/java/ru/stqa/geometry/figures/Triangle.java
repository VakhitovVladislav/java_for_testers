package ru.stqa.geometry.figures;



public record Triangle(double legOne, double legTwo, double hypotenuse) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return
                Double.compare(triangle.legOne, legOne) == 0 &&
                        Double.compare(triangle.legTwo, legTwo) == 0 &&
                        Double.compare(triangle.hypotenuse, hypotenuse) == 0 ||

                        Double.compare(triangle.legOne, legTwo) == 0 &&
                                Double.compare(triangle.legTwo, legOne) == 0 &&
                                Double.compare(triangle.hypotenuse, hypotenuse) == 0 ||

                        Double.compare(triangle.legOne, hypotenuse) == 0 &&
                                Double.compare(triangle.legTwo, legOne) == 0 &&
                                Double.compare(triangle.hypotenuse, legTwo) == 0 ||

                        Double.compare(triangle.legOne, legOne) == 0 &&
                                Double.compare(triangle.legTwo, hypotenuse) == 0 &&
                                Double.compare(triangle.hypotenuse, legTwo) == 0 ||

                        Double.compare(triangle.legOne, legTwo) == 0 &&
                                Double.compare(triangle.legTwo, hypotenuse) == 0 &&
                                Double.compare(triangle.hypotenuse, legOne) == 0 ||

                        Double.compare(triangle.legOne, hypotenuse) == 0 &&
                                Double.compare(triangle.legTwo, legTwo) == 0 &&
                                Double.compare(triangle.hypotenuse, legOne) == 0;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public Triangle {
        if ((legOne < 0 || legTwo < 0 || hypotenuse < 0)) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        } else if (legOne + legTwo < hypotenuse ||
                legOne + hypotenuse < legTwo || legTwo + hypotenuse < legOne) {
            throw new IllegalArgumentException("Added triangle angle (the sum of any two sides must " +
                    "be at least equal to the third side)");
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

