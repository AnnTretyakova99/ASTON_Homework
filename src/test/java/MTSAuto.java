import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MTSAuto {
    private static WebDriver driver;
    private static PaymentBlock mainPage;

    @BeforeAll
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        mainPage = new PaymentBlock(driver);

        mainPage.open();
        mainPage.acceptCookies();
    }

    @Order(1)
    @Test
    @DisplayName("1. Проверка названия блока")
    public void testBlockTitle() {
        String title = mainPage.getBlockTitleText();
        assertTrue(title.contains("Онлайн пополнение") && title.contains("без комиссии"),
                "Заголовок некорректен: " + title);
    }

    @Order(2)
    @Test
    @DisplayName("2. Проверка логотипов платёжных систем")
    public void testLogos() {
        int count = mainPage.getLogosCount();
        assertTrue(count > 0, "Логотипы не найдены");
    }

    @Order(3)
    @Test
    @DisplayName("3. Проверка ссылки 'Подробнее о сервисе'")
    public void testLink() {
        mainPage.clickMoreInfo();
        assertTrue(driver.getCurrentUrl().contains("help"), "Переход по ссылке не удался");
        driver.navigate().back(); // возвращаемся назад для следующего теста
    }

    @Order(4)
    @Test
    @DisplayName("4. Заполнение формы")
    public void testForms() {
        mainPage.fillPaymentForm("297777777", "10", "test@test.by");
        mainPage.clickContinue();
        assertTrue(mainPage.isPaymentIframePresent(), "Iframe оплаты не появился");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}