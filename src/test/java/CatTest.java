import com.example.Cat;
import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class CatTest {

    private Feline mockFeline; // Мок для класса Feline
    private Cat cat; // Объект для тестирования
    private List<String> expectedFood; // Ожидаемый результат
    private Exception expectedException; // Ожидаемое исключение для теста

    // Конструктор для параметризованного теста
    public CatTest(List<String> expectedFood, Exception expectedException) {
        this.expectedFood = expectedFood;
        this.expectedException = expectedException;
    }

    // Параметры для теста
    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { Arrays.asList("Курица", "Говядина"), null },
                { Arrays.asList("Рыба", "Молоко"), null },
                { null, new Exception("Ошибка при получении пищи") } // Тест на выброс исключения
        });
    }

    @Before
    public void setUp() {
        // Создаем мок для класса Feline
        mockFeline = Mockito.mock(Feline.class);

        // Создаем экземпляр кота, передаем мок в конструктор
        cat = new Cat(mockFeline);
    }

    @Test
    public void testGetFood() throws Exception {
        if (expectedException == null) {
            // Настроим мок, чтобы он возвращал переданный список
            when(mockFeline.eatMeat()).thenReturn(expectedFood);

            // Проверяем, что метод getFood() возвращает правильный результат
            List<String> actualFood = cat.getFood();
            assertEquals(expectedFood, actualFood);

            // Проверяем, что метод eatMeat() был вызван
            verify(mockFeline, times(1)).eatMeat();
        } else {
            // Настроим мок, чтобы он выбрасывал исключение
            when(mockFeline.eatMeat()).thenThrow(expectedException);

            // Проверяем, что при вызове getFood() будет выброшено исключение
            try {
                cat.getFood();
            } catch (Exception e) {
                assertEquals(expectedException.getMessage(), e.getMessage());
            }
        }
    }

    @Test
    public void testGetSound() {
        // Проверяем, что метод getSound() возвращает правильный звук
        assertEquals("Мяу", cat.getSound());
    }
}
