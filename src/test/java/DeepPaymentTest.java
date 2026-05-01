import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.lang.model.util.Types;
import java.time.Duration;
import java.util.List;

public class DeepPaymentTest {
    private WebDriver driver;
    private PaymentServicesPage paymentServicesPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();

        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");

        options.setPageLoadStrategy(PageLoadStrategy.NONE);

        driver = new org.openqa.selenium.chrome.ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
        driver.manage().window().maximize();

        paymentServicesPage = new PaymentServicesPage(driver);
        paymentServicesPage.open();
        paymentServicesPage.acceptCookies();
    }

    @Test(description = "1. Проверка плейсхолдеров всех вариантов оплаты")
    public void testPlaceholdersForAllTabs() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String[][] testSettings = {
                {"Услуги связи", "Номер телефона", "Сумма", "E-mail для отправки чека"},
                {"Домашний интернет", "Номер абонента", "Сумма", "E-mail для отправки чека"},
                {"Рассрочка", "Номер счета на 44", "Сумма", "E-mail для отправки чека"},
                {"Задолженность", "Номер счета на 2073", "Сумма", "E-mail для отправки чека"}
        };
        for (String[] setting : testSettings) {
            String tabName = setting[0];
            String expectedMain = setting[1];
            String expectedSum = setting[2];
            String expectedEmail = setting[3];

            paymentServicesPage.selectTab(tabName);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder, '" + expectedMain + "')]")));
            Assert.assertEquals(paymentServicesPage.getPlaceholderTextByLabel(expectedMain), expectedMain, "Ошибка основного поля на: " + tabName);
            Assert.assertEquals(paymentServicesPage.getPlaceholderTextByLabel(expectedSum), expectedSum, "Ошибка Суммы на: " + tabName);
            Assert.assertEquals(paymentServicesPage.getPlaceholderTextByLabel("E-mail"), expectedEmail, "Ошибка Email на: " + tabName);
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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pay-description__cost")));
        Assert.assertTrue(paymentServicesPage.getPaymentAmountText().contains(testSum), "Сумма не совпадает!");
        Assert.assertTrue(paymentServicesPage.getPaymentInfoText().contains(testPhone), "Номер не найден!");

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@src, 'bepaid') or contains(@src, 'checkout')]")));

        try {
            Thread.sleep(3000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*")));
            System.out.println("Успех: Контент формы оплаты загружен.");

        } catch (Exception e) {
            System.err.println("Форма оплаты так и не отобразила содержимое.");
            throw new RuntimeException(e);
        } finally {
            driver.switchTo().defaultContent();
        }
        }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}

