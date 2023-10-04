package ru.praktikum.qascooter.tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;


public class BaseTest {
    protected WebDriver driver;
    public static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
     //страница Яндекса URL
    public static final String YANDEX_HOME_PAGE_URL = "https://dzen.ru/?yredirect=true";

    @Before
    public void setUp() {
        setUpDriver("chrome"); // chrome OR firefox
        driver.manage().window().maximize();
        driver.get(MAIN_PAGE_URL);
        // Закрывать блок cookies внизу страницы
        WebElement acceptCookiesButton = driver.findElement(By.xpath(".//button[@id='rcc-confirm-button']"));
        acceptCookiesButton.click();

    }
    private void setUpDriver(String browser) {
        if ("chrome".equals(browser)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if ("firefox".equals(browser)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
