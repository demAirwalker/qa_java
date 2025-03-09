import com.example.Feline;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FelineTest {

    private final int inputKittens;

    public FelineTest(int inputKittens) {
        this.inputKittens = inputKittens;
    }

    @Parameterized.Parameters
    public static Object[] data() {
        return new Object[]{1, 2, 3};
    }

    @Test
    public void testGetKittensWithParameter() {
        Feline feline = new Feline();
        assertEquals(inputKittens, feline.getKittens(inputKittens));
    }

    @Test
    public void testGetKittensDefault() {
        Feline feline = new Feline();
        assertEquals(1, feline.getKittens());
    }

    @Test
    public void testGetFamily() {
        Feline feline = new Feline();
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void testEatMeat() throws Exception {
        Feline feline = Mockito.spy(new Feline());
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, feline.eatMeat());
        Mockito.verify(feline, Mockito.times(1)).eatMeat();
    }
}
