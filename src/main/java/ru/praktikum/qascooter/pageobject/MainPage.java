package ru.praktikum.qascooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;



public class MainPage extends BasePage {
    private WebDriver driver;

     // Локаторы
    private By question = By.className("accordion__button");
    private By answerText = By.xpath("//*[@class='accordion__panel']/p");


    // Конструктор
    public MainPage(WebDriver driver) {
        super(driver);
          this.driver = driver;
    }
    public MainPage openMainPage(){
        driver.get(MAIN_PAGE_URL);
        return this;
    }
    // Нажатие на стрелочку для раскрытия ответа на вопрос
    public void clickOnQuestion(int index) {
        List<WebElement> elements = driver.findElements(question);
        WebElement element = elements.get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    // Получение текста ответа на вопрос
    public String getAnswerText(int index) {
        List<WebElement> elements = driver.findElements(answerText);
        WebElement element = elements.get(index);
        return element.getText();
    }
    public void clickOrderButtonBottom() {
        WebElement element = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // Ждем и Кликаем по элементу
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
}
