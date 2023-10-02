package ru.praktikum.qascooter.tests;
import org.junit.Test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum.qascooter.pageobject.BasePage;
import ru.praktikum.qascooter.pageobject.MainPage;
import ru.praktikum.qascooter.pageobject.OrderPage;
import ru.praktikum.qascooter.pageobject.TrackPage;
import static org.junit.Assert.*;


import static org.junit.Assert.assertEquals;

public class CheckButtonsPath extends BaseTest {
    private OrderPage orderPage;
    private BasePage basePage;

    private int standardWaitTime = 10;

    @Test // Проверить: если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката».
    public void checkPathForLogoScooter() {
        orderPage = new OrderPage(driver);
        orderPage.clickOrderButtonTop();
        orderPage.clickOnLogoScooter();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(MAIN_PAGE_URL, currentUrl);

    }
    @Test // Проверить: если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса.
    public void checkPathForLogoYandex(){
        String originalWindow = driver.getWindowHandle();
        assertEquals(1, driver.getWindowHandles().size());  // Проверка количества открытых окон
        basePage = new BasePage(driver);
        basePage.clickOnLogoYandex();

        // Ожидание второго окна
        new WebDriverWait(driver, standardWaitTime).until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        // Ожидание загрузки URL
        new WebDriverWait(driver, standardWaitTime).until(ExpectedConditions.urlToBe(YANDEX_HOME_PAGE_URL));
        String expectedUrl = YANDEX_HOME_PAGE_URL;
        String actualUrl = driver.getCurrentUrl();
        assertEquals("Ошибка",expectedUrl, actualUrl);

        // Закрыть новое окно и вернуться обратно
        driver.close();
        driver.switchTo().window(originalWindow);

    }
    @Test //Проверить: если ввести неправильный номер заказа, попадёшь на страницу статуса заказа. На ней должно быть написано, что такого заказа нет.
    public void checkOrderStatusNotExistTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.statusOrderButtonClick();
        mainPage.enterOrderNumber("00000");
        mainPage.goButtonClick();
        TrackPage trackPage = new TrackPage(driver);
        Boolean isDisplayedNotFoundOrderImg = trackPage.isDisplayedNotFoundOrderImg();
        assertTrue(isDisplayedNotFoundOrderImg);
    }
}
