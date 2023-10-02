package ru.praktikum.qascooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

    protected WebDriver driver;
    public int standardWaitTime = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    public By orderButtonTop = By.xpath(".//button[@class='Button_Button__ra12g']");
    public By orderButtonBottom = By.xpath(".//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']");
    private By logoScooter = By.xpath(".//a[@class='Header_LogoScooter__3lsAR']");
    private By logoYandex = By.xpath(".//a[@class='Header_LogoYandex__3TSOI']");

    private By statusOrderButton = By.cssSelector("button.Header_Link__1TAG7");
    private By orderNumberInput = By.xpath(".//input[@type = 'text']");
    private By goButton = By.xpath(".//button[text()='Go!']");

    public void clickOnLogoScooter() {
        driver.findElement(logoScooter).click();
    }
    public void clickOnLogoYandex() {
        driver.findElement(logoYandex).click();
    }
    public void statusOrderButtonClick() {
        new WebDriverWait(driver, standardWaitTime)
                .until(ExpectedConditions.elementToBeClickable(statusOrderButton));
        driver.findElement(statusOrderButton).click();
    }    public void enterOrderNumber(String orderNumber){
        new WebDriverWait(driver, standardWaitTime)
                .until(ExpectedConditions.elementToBeClickable(orderNumberInput));
        WebElement orderNumberInputElement = driver.findElement(orderNumberInput);
        orderNumberInputElement.clear();
        orderNumberInputElement.sendKeys(orderNumber);
    }
    public void goButtonClick(){
        new WebDriverWait(driver, standardWaitTime)
                .until(ExpectedConditions.elementToBeClickable(goButton));
        driver.findElement(goButton).click();
    }

}