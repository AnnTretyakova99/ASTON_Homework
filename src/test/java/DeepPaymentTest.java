import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeepPaymentTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    public void testPlaceholderForAllTabs() {
        mainPage.selectTab("Услуги связи");
        Assert.assertEquals(mainPage.getPhonePlaceholder(),"Номер телефона");

        mainPage.selectTab("Домашний интерент");
        Assert.assertEquals(mainPage.getSumPlaceholder(),"Номер абонента");

        mainPage.selectTab("Рассрочка");
        Assert.assertEquals(mainPage.getPhonePlaceholder(),"Номер счёта на 44");

        mainPage.selectTab("Задолженность");
        Assert.assertEquals(mainPage.getPhonePlaceholder(),"Номер счёта на 2073");

        Assert.assertEquals(mainPage.getSumPlaceholder(),"Сумма");
    }

    @Test
    public void testFullPaymentCycle() {
        String testphone = "297777777";
        String testSum = "10.00";
        mainPage.selectTab("");
        mainPage.fillConnectionDetails(testphone, testSum, "test@mail.ru");
        mainPage.clickContinue();

        mainPage.switchToPaymentFrame();
        Assert.assertTrue(mainPage.getPaymentAmountText().contains(testSum));
        Assert.assertTrue(mainPage.getPayButtonText().contains(testSum));
        Assert.assertTrue(mainPage.isCardNumberLabelVisible(), "Метка 'Номер карты' не видна");
        Assert.assertTrue(mainPage.getPaymentIconsCount() > 0, "Иконки систем оплаты не загрузились");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
