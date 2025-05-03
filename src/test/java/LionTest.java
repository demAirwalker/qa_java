import com.example.Feline;
import com.example.Lion;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class LionTest {

    @Parameterized.Parameter(0)
    public String sex;

    @Parameterized.Parameter(1)
    public Boolean expectedHasMane;

    @Parameterized.Parameter(2)
    public boolean shouldThrow;

    @Parameterized.Parameters(name = "{index}: sex={0}, expectedHasMane={1}, shouldThrow={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Самец", true, false},
                {"Самка", false, false},
                {"Неизвестно", null, true},
                {null, null, false} // этот набор используется ТОЛЬКО для обычных тестов
        });
    }

    @Mock
    private Feline feline;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Параметризованный тест на hasMane и исключения
    @Test
    public void testLionManeOrException() {
        // Пропускаем этот тест, если это технический запуск для других методов
        if (sex == null) return;

        try {
            Lion lion = new Lion(sex, feline);
            if (shouldThrow) {
                fail("Ожидалось исключение для пола: " + sex);
            } else {
                assertEquals(expectedHasMane, lion.doesHaveMane());
            }
        } catch (Exception e) {
            if (!shouldThrow) {
                fail("Не должно быть исключения, но оно было: " + e.getMessage());
            }
        }
    }

    // Обычный тест — проверка делегирования getKittens
    @Test
    public void testGetKittensDelegatesToFeline() throws Exception {
        if (sex != null) return; // запускаем только при sex == null
        when(feline.getKittens()).thenReturn(3);
        Lion lion = new Lion("Самец", feline);
        assertEquals(3, lion.getKittens());
        verify(feline).getKittens();
    }

    // Обычный тест — проверка делегирования getFood
    @Test
    public void testGetFoodDelegatesToFeline() throws Exception {
        if (sex != null) return; // запускаем только при sex == null
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        when(feline.getFood("Хищник")).thenReturn(expectedFood);
        Lion lion = new Lion("Самка", feline);
        assertEquals(expectedFood, lion.getFood());
        verify(feline).getFood("Хищник");
    }
}
