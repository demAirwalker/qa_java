import com.example.Lion;
import com.example.Feline;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    private Feline feline;
    private Lion lion;

    @Before
    public void setUp() throws Exception {
        lion = new Lion("Самец", feline);
    }

    @Test
    public void testLionMane() throws Exception {
        lion = new Lion("Самец", feline);
        assertTrue(lion.doesHaveMane());

        lion = new Lion("Самка", feline);
        assertFalse(lion.doesHaveMane());
    }

    @Test
    public void testGetKittens() throws Exception {
        when(feline.getKittens()).thenReturn(3);

        lion = new Lion("Самец", feline);
        assertEquals(3, lion.getKittens());
    }

    @Test
    public void testGetFood() throws Exception {
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        when(feline.getFood("Хищник")).thenReturn(expectedFood);

        lion = new Lion("Самец", feline);
        List<String> food = lion.getFood();

        assertEquals(expectedFood, food);
    }

    @RunWith(org.junit.runners.Parameterized.class)
    public static class LionParameterizedSexTest {

        private String sex;
        private boolean expectedHasMane;

        public LionParameterizedSexTest(String sex, boolean expectedHasMane) {
            this.sex = sex;
            this.expectedHasMane = expectedHasMane;
        }

        @Parameterized.Parameters
        public static Object[][] data() {
            return new Object[][] {
                    { "Самец", true },
                    { "Самка", false }
            };
        }

        @Test
        public void testLionConstructorWithParameterizedInput() throws Exception {
            Feline mockFeline = Mockito.mock(Feline.class);
            Lion lion = new Lion(sex, mockFeline);
            assertEquals(expectedHasMane, lion.doesHaveMane());
        }
    }
}