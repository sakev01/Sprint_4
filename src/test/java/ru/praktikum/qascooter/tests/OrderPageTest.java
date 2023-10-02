package ru.praktikum.qascooter.tests;
import org.junit.Test;
import ru.praktikum.qascooter.pageobject.MainPage;
import ru.praktikum.qascooter.pageobject.OrderPage;
import static org.junit.Assert.assertTrue;



public class OrderPageTest extends BaseTest{

    private OrderPage orderPage;
    private MainPage mainPage;
    private String orderCompletedSuccess = "Заказ оформлен";

    @Test // Заполнить форму Заказ самоката через кнопку «Заказать» вверху
    public void testFillOderViaButtonTop() {
        orderPage = new OrderPage(driver);
        orderPage.clickOrderButtonTop();
        orderPage.fillNameFieldInput("Васёк");
        orderPage.fillSurnameFieldInput("Мамаев");
        orderPage.fillAddressFieldInput("ул. Льва Толстого, 16, Москва");
        orderPage.selectSubwayStation("Сокол");
        orderPage.fillPhoneFieldInput("79619650001");
        orderPage.clickButtonNext();
        orderPage.selectDeliveryDate("11.10.2023");
        orderPage.selectRentLength("двое суток");
        orderPage.selectColorBlack();
        orderPage.fillCommentsForDeliveryGuy ("Быстрее");
        orderPage.pressSubmitButton();
        orderPage.pressYesButton();
        orderPage.popupMessage();
        String popupMessage = orderPage.popupMessage();
        assertTrue(popupMessage.contains(orderCompletedSuccess));

    }
    @Test // Заполнить форму Заказ самоката через кнопку «Заказать» внизу
    public void testFillOderViaButtonBottom() {
        orderPage = new OrderPage(driver);
        mainPage = new MainPage(driver);
        mainPage.clickOrderButtonBottom();
        orderPage.fillNameFieldInput("Васёк");
        orderPage.fillSurnameFieldInput("Мамаев");
        orderPage.fillAddressFieldInput("ул. Льва Толстого, 16, Москва");
        orderPage.selectSubwayStation("Сокол");
        orderPage.fillPhoneFieldInput("79619650001");
        orderPage.clickButtonNext();
        orderPage.selectDeliveryDate("11.12.2023");
        orderPage.selectRentLength("двое суток");
        orderPage.selectColorGrey();
        orderPage.fillCommentsForDeliveryGuy ("Быстрее");
        orderPage.pressSubmitButton();
        orderPage.pressYesButton();
        orderPage.popupMessage();
        String popupMessage = orderPage.popupMessage();
        assertTrue(popupMessage.contains(orderCompletedSuccess));

    }
    @Test // Форма Заказ самоката: Проверить ошибки - Введите корректный номер
    public void testEmptyPhoneNumberError() {
        orderPage = new OrderPage(driver);
        orderPage.clickOrderButtonTop();
        orderPage.fillNameFieldInput("Васёк");
        orderPage.fillSurnameFieldInput("Мамаев");
        orderPage.fillAddressFieldInput("ул. Льва Толстого, 16, Москва");
        orderPage.selectSubwayStation("Сокол");
        orderPage.pressSubmitButton();
        String errorMessagePhoneNumber = orderPage.getPhoneNumberFieldError();
        assertTrue(errorMessagePhoneNumber.equals("Введите корректный номер"));
    }
    @Test // Форма Заказ самоката: Проверить ошибки номер - Выберите станцию
    public void testEmptySubwayStationError() {
        orderPage = new OrderPage(driver);
        orderPage.clickOrderButtonTop();
        orderPage.fillNameFieldInput("Васёк");
        orderPage.fillSurnameFieldInput("Мамаев");
        orderPage.fillAddressFieldInput("ул. Льва Толстого, 16, Москва");
        orderPage.pressSubmitButton();
        String errorSubwayStationError = orderPage.getSubwayStationError();
        assertTrue(errorSubwayStationError.equals("Выберите станцию"));
    }
    @Test //Форма Заказ самоката:  Проверить ошибки номер - Введите корректное имя
    public void testEmptyNameFieldError(){
        orderPage = new OrderPage(driver);
        orderPage.clickOrderButtonTop();
        orderPage.fillNameFieldInput("");
        orderPage.pressSubmitButton();
        String errorMessageName = orderPage.getEmptyNameFieldErrorMessage();
        assertTrue(errorMessageName.equals("Введите корректное имя"));

    }
    @Test //Форма Заказ самоката:  Проверить ошибки номер - Введите корректную фамилию
    public void testEmptySurnameFieldError(){
        orderPage = new OrderPage(driver);
        orderPage.clickOrderButtonTop();
        orderPage.fillNameFieldInput("Васёк");
        orderPage.fillSurnameFieldInput("");
        orderPage.pressSubmitButton();
        String errorMessageSurname = orderPage.getSurnameFieldError();
        assertTrue(errorMessageSurname.equals("Введите корректную фамилию"));
    }
    @Test //Форма Заказ самоката:  Проверить ошибки номер - Введите корректный адрес
    public void testEmptyAddressFieldError() {
        orderPage = new OrderPage(driver);
        orderPage.clickOrderButtonTop();
        orderPage.fillNameFieldInput("Васёк");
        orderPage.fillSurnameFieldInput("Мамаев");
        orderPage.fillAddressFieldInput("0");
        orderPage.pressSubmitButton();
        String errorMessageName = orderPage.getAddressFieldError();
        assertTrue(errorMessageName.equals("Введите корректный адрес"));
    }
    /*        Не могу понять, как сделать проверку для input на второй странице заказа.
        Так как там нет всплывающего сообщения, можно сделать проверку через assert на окно 'Заказ выполнен'.
        Однако сайт позволяет делать заказ без заполнения всех полей.*/

        @Test //Форма Заказ самоката:  Комментарий для курьера - Тут что-то не так
        public void testEmptyCommentsDeliveryField() {
            orderPage = new OrderPage(driver);
            orderPage.clickOrderButtonTop();
            orderPage.fillNameFieldInput("Васёк");
            orderPage.fillSurnameFieldInput("Мамаев");
            orderPage.fillAddressFieldInput("ул. Льва Толстого, 16, Москва");
            orderPage.selectSubwayStation("Сокол");
            orderPage.fillPhoneFieldInput("79619650001");
            orderPage.clickButtonNext();
            orderPage.selectDeliveryDate("11.10.2023");
            orderPage.selectRentLength("двое суток");
            orderPage.selectColorBlack();
            orderPage.fillCommentsForDeliveryGuy ("");
            orderPage.pressSubmitButton();
            String errorCommentsDeliveryField = orderPage.getCommentsDeliveryFieldError();
            assertTrue(errorCommentsDeliveryField.equals("Тут что-то не так"));
        }

}


