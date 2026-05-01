import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PaymentBlock {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By blockTitle = By.xpath("//section[contains(@class,'pay')]//h2");
    private final By partnersLogos = By.cssSelector(".pay__partners img");
    private final By moreInfoLink = By.linkText("Подробнее о сервисе");
    private final By cookieBtn = By.id("cookie-confirm");


    private final By phoneInput = By.id("connection-phone");
    private final By sumInput = By.id("connection-sum");
    private final By emailInput = By.id("connection-email");
    private final By submitButton = By.xpath("//form[@id='pay-connection']//button[contains(text(),'Продолжить')]");

    public PaymentBlock(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void open() {
        driver.get("https://www.mts.by");
    }

    public void acceptCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cookieBtn)).click();
        } catch (Exception e) {
            System.out.println("Окно куки не появилось");
        }
    }

    public String getBlockTitleText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitle)).getText();
    }

    public int getLogosCount() {
        return driver.findElements(partnersLogos).size();
    }

    public void clickMoreInfo() {
        driver.findElement(moreInfoLink).click();
    }

    public void fillPaymentForm(String phone, String sum, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput)).sendKeys(phone);
        driver.findElement(sumInput).sendKeys(sum);
        driver.findElement(emailInput).sendKeys(email);
    }

    public void clickContinue() {
        driver.findElement(submitButton).click();
    }

    public boolean isPaymentIframePresent() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe"))).isDisplayed();
    }
}
