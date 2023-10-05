package ru.praktikum.qascooter.pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TrackPage extends BasePage{

    // Картинка заказ не найден
    private By notFoundImg = By.cssSelector("div.Track_NotFound__6oaoY >img");

    public TrackPage(WebDriver driver){
        super(driver);
    }

    public Boolean isDisplayedNotFoundOrderImg(){
        new WebDriverWait(driver, standardWaitTime)
                .until(ExpectedConditions.visibilityOfElementLocated(notFoundImg));
        return driver.findElement(notFoundImg).isDisplayed();
    }
}
