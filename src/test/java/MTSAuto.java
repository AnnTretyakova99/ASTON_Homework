import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MTSAuto {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // ВАЖНО: Инициализируем wait СРАЗУ после драйвера
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://mts.by");

        try {
            List<WebElement> cookieButtons = driver.findElements(By.id("cookie-agree"));
            if (!cookieButtons.isEmpty()) {
                cookieButtons.get(0).click();
            }
        } catch (Exception e) {
            System.out.println("Куки не найдены, идем дальше");
        }
    }

    @Test
    @DisplayName("1. Проверка названия блока")
    public void testBlockTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//section[contains(@class,'pay')]//h2")
        ));
        String actualText = title.getText().replace("\n", " ").trim();
        assertEquals("Онлайн пополнение без комиссии", actualText);
    }

    @Test
    @DisplayName("2. Проверка логотипов")
    public void testLogos() {
        List<WebElement> logos = driver.findElements(By.xpath("//div[@class='pay__partners']//img"));
        assertTrue(logos.size() > 0, "Логотипы не найдены");
    }

    @Test
    @DisplayName("3. Проверка ссылки Подробнее")
    public void testLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Подробнее о сервисе")));
        link.click();
        assertTrue(driver.getCurrentUrl().contains("help"), "Ссылка не сработала");
        driver.navigate().back(); // Возвращаемся для 4 теста
    }

    @Test
    @DisplayName("4. Заполнение формы")
    public void testForms() {
        WebElement phone = wait.until(ExpectedConditions.elementToBeClickable(By.id("connection-phone")));
        phone.sendKeys("297777777");

        WebElement sum = driver.findElement(By.id("connection-sum"));
        sum.sendKeys("10");

        WebElement email = driver.findElement(By.id("connection-email"));
        email.sendKeys("test@test.by");

        WebElement btn = driver.findElement(By.xpath("//form[@id='pay-connection']//button[contains(text(),'Продолжить')]"));
        wait.until(ExpectedConditions.elementToBeClickable(btn));
        btn.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
        assertTrue(true);

    }
}