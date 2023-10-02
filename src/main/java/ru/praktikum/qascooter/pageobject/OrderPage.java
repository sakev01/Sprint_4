package ru.praktikum.qascooter.pageobject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
public class OrderPage extends BasePage {
    public OrderPage(WebDriver driver){
        super(driver);
    }
     // Локаторы для полей ввода в форме заказа
    private By nameFieldInput = By.xpath(".//input[@placeholder='* Имя']");
    private By surnameFieldInput = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressFieldInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By phoneFieldInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By rentLength = By.xpath(".//*[@class='Dropdown-placeholder']");
    private By colorSelectionBlack = By.xpath(".//input[@id='black']");
    private By colorSelectionGrey = By.xpath(".//input[@id='grey']");
    private By commentsForDeliveryGuy = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']");

    // Кнопки для заказа в форме заказа
    private By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private By yesButton = By.xpath(".//div[@class='Order_Modal__YZ-d3']//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private By submitButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Сообщение если поле не заполнено или incorrect value
    private By nameFieldError = By.xpath("//div[text()='Введите корректное имя']");
    private By surnameFieldError = By.xpath("//div[text()='Введите корректную фамилию']");
    private By phoneNumberFieldError = By.xpath("//div[text()='Введите корректный номер']");

    private By subwayStationError = By.xpath("//div[@class='Order_MetroError__1BtZb']");

    private By addressFieldInputError = By.xpath("//div[text()='Введите корректный адрес']");
    private By сommentsDeliveryFieldError= By.xpath("//div[text()='Тут что-то не так']");

    // Нажать на верх. кнопку заказа
    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }
  //общий метод для получения текста ошибки
    public String getErrorMessage(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, standardWaitTime);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }
    public String getSubwayStationError() {
        return getErrorMessage(subwayStationError);
    }
    public String getPhoneNumberFieldError() {
        return getErrorMessage(phoneNumberFieldError);
    }
    public String getEmptyNameFieldErrorMessage() {
        return getErrorMessage(nameFieldError);
    }
    public String getSurnameFieldError() {
        return getErrorMessage(surnameFieldError);
    }
    public String getAddressFieldError() {
        return getErrorMessage(addressFieldInputError);
    }
    public String getCommentsDeliveryFieldError() {
        return getErrorMessage(сommentsDeliveryFieldError);
    }


    // Заполнить Имя
    public void fillNameFieldInput(String name){
        driver.findElement(nameFieldInput).sendKeys(name);
    }
    // Заполнить Фамилию
    public void fillSurnameFieldInput(String surname){
        driver.findElement(surnameFieldInput).sendKeys(surname);
    }
    // Заполнить Адрес
    public void fillAddressFieldInput(String address){
        driver.findElement(addressFieldInput).sendKeys(address);
    }
    // Заполнить номер
    public void fillPhoneFieldInput (String phoneNumber) {
        driver.findElement(phoneFieldInput).sendKeys(phoneNumber);
    }
    //Нажать на кнопку Далее
    public void clickButtonNext(){
        driver.findElement(buttonNext).click();
    }

    //Выбрать станцию метро
    public void selectSubwayStation(String stationName) {
        // Открываем выпадающий список для выбора метро
        WebElement metroSelect = driver.findElement(By.cssSelector(".select-search"));
        metroSelect.click();
        // Вводим название метро в поле поиска
        WebElement metroInput = driver.findElement(By.xpath(".//input[contains(@placeholder, '* Станция метро')]"));
        metroInput.sendKeys(stationName);
        // Выбираем станцию метро из выпадающего списка и кликаем
        String metroOptionTemplate = ".//div[@class='select-search__select']//*[text()='%s']";
        List<WebElement> metroLists = driver.findElements(By.xpath(String.format(metroOptionTemplate, stationName)));
        metroLists.get(0).click();
    }

    // Выбор даты - Когда привезти самокат
    public void selectDeliveryDate(String date) {
        WebElement dropdownCalendarSelect = driver.findElement(By.xpath(".//input[@placeholder='* Когда привезти самокат']"));
        dropdownCalendarSelect.sendKeys(date);
        dropdownCalendarSelect.sendKeys(Keys.ENTER);
    }
// Выбор Срок аренды
    public void selectRentLength(String rentOption){
        WebElement dropdown = driver.findElement(rentLength);
        dropdown.click();
        By rentLengthElement = By.xpath(String.format("//div[contains(@class,'Dropdown-option') and text()='%s']", rentOption));
        driver.findElement(rentLengthElement).click();
    }
    // Цвет самоката выбрать - Черный
    public void selectColorBlack(){
        driver.findElement(colorSelectionBlack).click();
    }
    // Цвет самоката выбрать - Серый
    public void selectColorGrey(){
        driver.findElement(colorSelectionGrey).click();
    }
    // Комментарий для курьера написать
    public void fillCommentsForDeliveryGuy (String comments){
        driver.findElement(commentsForDeliveryGuy).click();
        driver.findElement(commentsForDeliveryGuy).sendKeys(comments);
    }
    // Кнопка Заказать - Нажать
    public void pressSubmitButton(){
        driver.findElement(submitButton).click();
    }
    // Кнопка Да - Хотите оформить заказ?
    public void pressYesButton(){
        new WebDriverWait(driver, standardWaitTime).until(ExpectedConditions.visibilityOfElementLocated(yesButton));
        driver.findElement(yesButton).click();
    }

    // popup - Заказ оформлен
    public String popupMessage() {
        By successPopup = By.xpath("//div[contains(@class, 'Order_ModalHeader__3FDaJ')][contains(text(), 'Заказ оформлен')]");
        // Ожидание появления всплывающего окна
    new WebDriverWait(driver, standardWaitTime).until(ExpectedConditions.visibilityOfElementLocated(successPopup));
        // Проверка текста во всплывающем окне
        String successText = driver.findElement(successPopup).getText();
        return successText;
    }
}

