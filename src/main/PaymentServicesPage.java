import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PaymentServicesPage {
private final WebDriver driver;
private final WebDriverWait wait;

private final By connectionTab = By.xpath("//buttin[text()='Услуги связи']");
private final By internetTab = By.xpath("//button[text()='Домашний интернет']");
private final By installmentTab = By.xpath("//button[text()='Расрочка']");
private final By debTab = By.xpath("//button[text()= 'Задолженность']");

private final By phoneInput = By.id("connection-phone");
private final By sumInput = By.id("connection-sum");
private final By emailInput = By.id("connection-email");
private final By submitButton = By.xpath("//from[@id='pay-connection']//button[contains(text(),'Продолжить')]");

private final By paymentFrame = By.cssSelector("iframe.bepaid-iframe");
private final By paymentAmount = By.cssSelector(".pay-description__cost");
private final By payButton = By.cssSelector(".pay-button");
private final By cardNumberLabel = By.xpath("//label[contains(@class, 'field-label') and contains(text(), 'Номер карты')]");
private final By paymentIcons = By.cssSelector(".payment-page__icons img");

public PaymentServicesPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
}

public void open() {
    driver.get("https://mts.by");
}

public void selectTab(String tabName) {
    By locator;
    switch (tabName) {
        case "Услуги связи": locator = connectionTab; break;
        case "Домашний интеренет": locator = internetTab; break;
        case "Рассрочка": locator = installmentTab; break;
        case "Задолженность": locator = debTab; break;
        default: throw new IllegalArgumentException("Неверная вкладка: " + tabName);
    }
    driver.findElement(locator).click();
}

public String getPhonePlaceholder() {
    return driver.findElement(phoneInput).getAttribute("placeholder");
}
public String getSumPlaceholder() {
    return driver.findElement(sumInput).getAttribute("placeholder");
}
public void fillConnectionDetails(String phone, String sum, String email) {
    driver.findElement(phoneInput).sendKeys(phone);
    driver.findElement(sumInput).sendKeys(sum);
    driver.findElement(emailInput).sendKeys(email);
}
public void clickContinue() {
    driver.findElement(submitButton).click();
}
public void switchToPaymentFrame() {
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentFrame));
}
public String getPaymentAmountText() {return driver.findElement(paymentAmount).getText(); }
    public String getPayButtonText() { return driver.findElement(payButton).getText(); }
    public boolean isCardNumberLabelVisible() { return driver.findElement(cardNumberLabel).isDisplayed(); }
    public int getPaymentIconsCount() { return driver.findElements(paymentIcons).size(); }
}
