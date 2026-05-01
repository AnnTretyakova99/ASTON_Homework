import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.lang.model.util.Types;

public class DeepPaymentTest {
    private WebDriver driver;
    private PaymentServicesPage paymentServicesPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();

        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");

        driver = new org.openqa.selenium.chrome.ChromeDriver(options);
        driver.manage().window().maximize();

        paymentServicesPage = new PaymentServicesPage(driver);
        paymentServicesPage.open();
        paymentServicesPage.acceptCookies();
    }

    @Test(description = "1. Проверка плейсхолдеров всех вариантов оплаты")
    public void testPlaceholdersForAllTabs() {
        String[][] testSettings = {
                {"Услуги связи", "Номер телефона"},
                {"Домашний интернет", "Номер абонента"},
                {"Рассрочка", "Номер счета на 44"},
                {"Задолженность", "Номер счета на 2073"}
        };

        for (String[] setting : testSettings) {
            String tabName = setting[0];
            String expectedPlaceholder = setting[1];

            paymentServicesPage.selectTab(tabName);
            String actualPlaceholder = paymentServicesPage.getActivePlaceholder();

            Assert.assertEquals(actualPlaceholder, expectedPlaceholder, "Ошибка на вкладке: " + tabName);
        }
    }

    @Test(description = "2. Проверка Услуг связи и содержимого окна оплаты")
    public void testFullPaymentCycle() {
        String testPhone = "297777777";
        String testSum = "10.00";
        paymentServicesPage.selectTab("Услуги связи");
        paymentServicesPage.fillPaymentDetails(testPhone, testSum, "test@mail.ru");
        paymentServicesPage.clickContinue();
        paymentServicesPage.switchToPaymentFrame();

        Assert.assertTrue(paymentServicesPage.getPaymentAmountText().contains(testSum), "Сумма в окне не совпадает!");
        Assert.assertTrue(paymentServicesPage.getPaymentInfoText().contains(testPhone), "Номер телефона не найден!");

        Assert.assertTrue(paymentServicesPage.isFieldVisible("cc-number"), "Поле номера карты не найдено");
        Assert.assertTrue(paymentServicesPage.isFieldVisible("cvv"), "Поле CVC не найдено");

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}