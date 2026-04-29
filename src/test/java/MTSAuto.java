import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MTSAuto {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://mts.by");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class,'pay')]//h2")));
        try {
            List<WebElement> cookieButtons = driver.findElements(By.id("cookie-agree"));
            if (!cookieButtons.isEmpty()) {
                cookieButtons.get(0).click();
            }
        } catch (Exception e) {
            System.out.println("Куки не найдены");
        }
    }

    @Order(1)
    @Test
    @DisplayName("1. Проверка названия блока")
    public void testBlockTitle() {
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//section[contains(@class,'pay')]//h2")
        ));
        String actualTitle = titleElement.getText();
        assertTrue(actualTitle.contains("Онлайн пополнение") && actualTitle.contains("без комиссии"),
                "Заголовок не содержит нужных слов! Получили: [" + actualTitle + "]");
    }

    @Order(2)
    @Test
    @DisplayName("2. Проверка логотипов платёжных систем")
    public void testLogos() {
        WebElement partnersBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".pay__partners")
        ));
        List<WebElement> logos = partnersBlock.findElements(By.tagName("img"));
        assertTrue(logos.size() > 0, "Логотипы не найдены");
        System.out.println("Количество найденных логотипов: " + logos.size());
    }

    @Order(3)
    @Test
    @DisplayName("3. Проверка ссылки 'Подробнее о сервисе' ")
    public void testLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Подробнее о сервисе")));
        link.click();
        assertTrue(driver.getCurrentUrl().contains("help"), "Ссылка не сработала");
    }

    @Order(4)
    @Test
    @DisplayName("4. Заполнение формы")
    public void testForms() {
        driver.get("https://mts.by");
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