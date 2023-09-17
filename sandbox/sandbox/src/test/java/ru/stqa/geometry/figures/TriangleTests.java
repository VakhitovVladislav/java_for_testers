package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {



    @Test
    void canCalculateArea(){
        var t = new Triangle(2.0, 3.0, 4.0);
       Assertions.assertEquals(2.9047375096555625, new Triangle(2.0,3.0,4.0).triangleArea());
    }

    @Test
    void canCalculatePerimeter(){
        Assertions.assertEquals(9, new Triangle(2.0, 3.0, 4.0).trianglePerimeter());
    }
}
