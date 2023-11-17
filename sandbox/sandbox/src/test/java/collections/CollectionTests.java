package collections;

import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import java.util.*;

public class CollectionTests {

    @Test
    void arrayTests(){
        //var array = new String[]{"a", "b", "c"};
        var array = new String[3];
        Assertions.assertEquals("a", array[0]);

        array[0] = "d";
        Assertions.assertEquals("a", array[0]);
    }

    @Test
    void listTests(){
        var list = new ArrayList<>(List.of("a", "b", "c"));

        list.add("a");
        list.add("b");
        list.add("c");
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("a", list.get(0));

        list.set(0, "d");
        Assertions.assertEquals("d", list.get(0));

    }
    @Test
    void setTests(){
        var set = new HashSet<>(List.of("a", "b", "c"));
        Assertions.assertEquals(3, set.size());
        var element = set.stream()
                .findFirst().get();

    }
    @Test
    void testMap(){
        Map<Character, String> digits = new HashMap<>();
        digits.put('1', "one");
        digits.put('2', "two");
        digits.put('3', "three");
        Assertions.assertEquals("one", digits.get('1'));
        digits.put('1', "один");
        Assertions.assertEquals("один", digits.get('1'));
    }

}
