import org.junit.jupiter.api.Test;

public class MathTests {
    @Test
    void testDivideByZero(){
        int x = 1;
        int y = 2;
        int z = x / y;
        System.out.println(z);
    }
}
