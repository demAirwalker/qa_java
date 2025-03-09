import com.example.Cat;
import com.example.Feline;
import com.example.Predator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CatTest {

    private final List<String> expectedFood;

    public CatTest(List<String> expectedFood) {
        this.expectedFood = expectedFood;
    }

    @Parameterized.Parameters
    public static Object[] data() {
        return new Object[]{Arrays.asList("Животные", "Птицы", "Рыба")};
    }

    @Test
    public void testGetSound() {
        Cat cat = new Cat(new Feline());
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    public void testGetFood() throws Exception {
        Feline felineSpy = Mockito.spy(new Feline());

        // Use spy and mock the behavior only for the getFood method
        Mockito.doReturn(expectedFood).when(felineSpy).getFood("Хищник");

        Cat cat = new Cat(felineSpy);

        List<String> actualFood = cat.getFood();
        System.out.println("Actual Food: " + actualFood);

        assertEquals(expectedFood, actualFood);
        Mockito.verify(felineSpy, Mockito.times(1)).getFood("Хищник");
    }
}
