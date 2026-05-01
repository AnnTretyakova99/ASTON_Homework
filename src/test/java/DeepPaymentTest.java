import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeepPaymentTest {
    private WebDriver driver;
    private PaymentServicesPage paymentServicesPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        paymentServicesPage = new PaymentServicesPage(driver);
        paymentServicesPage.open();
    }

    @Test
    public void testPlaceholderForAllTabs() {
        paymentServicesPage.selectTab("Услуги связи");
        Assert.assertEquals(paymentServicesPage.getPhonePlaceholder(),"Номер телефона");

        paymentServicesPage.selectTab("Домашний интерент");
        Assert.assertEquals(paymentServicesPage.getSumPlaceholder(),"Номер абонента");

        paymentServicesPage.selectTab("Рассрочка");
        Assert.assertEquals(paymentServicesPage.getPhonePlaceholder(),"Номер счёта на 44");

        paymentServicesPage.selectTab("Задолженность");
        Assert.assertEquals(paymentServicesPage.getPhonePlaceholder(),"Номер счёта на 2073");

        Assert.assertEquals(paymentServicesPage.getSumPlaceholder(),"Сумма");
    }

    @Test
    public void testFullPaymentCycle() {
        String testphone = "297777777";
        String testSum = "10.00";
        paymentServicesPage.selectTab("");
        paymentServicesPage.fillConnectionDetails(testphone, testSum, "test@mail.ru");
        paymentServicesPage.clickContinue();

        paymentServicesPage.switchToPaymentFrame();
        Assert.assertTrue(paymentServicesPage.getPaymentAmountText().contains(testSum));
        Assert.assertTrue(paymentServicesPage.getPayButtonText().contains(testSum));
        Assert.assertTrue(paymentServicesPage.isCardNumberLabelVisible(), "Метка 'Номер карты' не видна");
        Assert.assertTrue(paymentServicesPage.getPaymentIconsCount() > 0, "Иконки систем оплаты не загрузились");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
