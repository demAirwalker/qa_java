import com.example.Feline;
import com.example.Animal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class FelineTest {

    private Feline feline; // Экземпляр для тестирования
    private Animal mockAnimal; // Мок для класса Animal
    private List<String> expectedFood; // Ожидаемый результат для теста eatMeat
    private Integer expectedKittens; // Ожидаемое количество котят для тестов
    private Integer kittensCountInput; // Входное значение для getKittens(int)

    // Конструктор для параметризованного теста
    public FelineTest(List<String> expectedFood, Integer expectedKittens, Integer kittensCountInput) {
        this.expectedFood = expectedFood;
        this.expectedKittens = expectedKittens;
        this.kittensCountInput = kittensCountInput;
    }

    // Параметры для теста
    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // Тест для eatMeat
                { Arrays.asList("Курица", "Говядина"), 1, null },
                // Тесты для getKittens и getKittens(int)
                { null, 1, null }, // Тест для getKittens()
                { null, 5, 5 } // Тест для getKittens(int)
        });
    }

    @Before
    public void setUp() {
        // Создаем реальный экземпляр Feline для тестирования
        feline = new Feline();
        // Создаем мок для класса Animal (если нужно будет использовать его в будущем)
        mockAnimal = Mockito.mock(Animal.class);
    }

    @Test
    public void testEatMeatPositive() throws Exception {
        // Arrange
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        Feline feline = new Feline();
        Feline mockFeline = Mockito.spy(feline);

        doReturn(expectedFood).when(mockFeline).getFood("Хищник");

        // Act
        List<String> actualFood = mockFeline.eatMeat();

        // Assert
        assertEquals(expectedFood, actualFood);
        verify(mockFeline, times(1)).getFood("Хищник");
    }

    @Test
    public void testEatMeatNegative() throws Exception {
        // Arrange
        Feline feline = new Feline();
        Feline mockFeline = Mockito.spy(feline);

        doReturn(null).when(mockFeline).getFood("Хищник");

        // Act
        List<String> actualFood = mockFeline.eatMeat();

        // Assert
        assertNull(actualFood);
        verify(mockFeline, times(1)).getFood("Хищник");
    }


    @Test
    public void testGetFamily() {
        // Проверяем, что метод getFamily возвращает строку "Кошачьи"
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void testGetKittens() {
        // Проверяем, что метод getKittens возвращает правильное количество котят
        assertEquals((int) expectedKittens, feline.getKittens()); // Явное приведение к int
    }

    @Test
    public void testGetKittensWithCount() {
        // Проверяем, что метод getKittens(int) работает правильно
        if (kittensCountInput != null) {
            assertEquals((int) kittensCountInput, feline.getKittens(kittensCountInput));
        }
    }
}
