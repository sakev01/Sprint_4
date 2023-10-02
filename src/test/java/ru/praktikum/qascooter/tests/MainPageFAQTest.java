package ru.praktikum.qascooter.tests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.qascooter.pageobject.MainPage;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MainPageFAQTest extends BaseTest {
    private MainPage mainPage;
    private int questionIndex;
    private String expectedAnswer;

    //Конструктор, который будет менять поля
    public MainPageFAQTest(int questionIndex, String expectedAnswer) {
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
    }
    // Тестовые данные
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."}
        });
    }

    @Test
    public void testFAQSection() {
        mainPage= new MainPage(driver);
        mainPage.clickOnQuestion(questionIndex);  // Нажмите на вопрос
        String actualText = mainPage.getAnswerText(questionIndex); // Получите текст ответа
        assertEquals(expectedAnswer, actualText);
        System.out.println("success");
    }
}
