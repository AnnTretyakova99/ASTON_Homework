package mts;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import io.qameta.allure.Step;

public class PaymentServicesPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    private final By cookieBtn = By.id("cookie-agree");
    private final By selectHeader = By.cssSelector(".select__header");
    private final By activeAccountInput = By.xpath("//form[contains(@class,'opened')]//input[not(@type='hidden') and not(contains(@id,'sum')) and not(@type='email')]");
    private final By activeSumInput = By.xpath("//form[contains(@class,'opened')]//input[contains(@id,'sum')]");
    private final By activeEmailInput = By.xpath("//form[contains(@class,'opened')]//input[contains(@id,'email')]");
    private final By submitButton = By.xpath("//form[contains(@class,'opened')]//button[contains(text(),'Продолжить')]");


    private final By iframe = By.tagName("iframe");
    private final By paymentAmount = By.xpath("//*[contains(@class, 'pay-description__cost')]");
    private final By infoText = By.xpath("//*[contains(@class, 'pay-description__text')]");
    private final By payButton = By.cssSelector("button.pay-btn");
    private final By cardIcons = By.cssSelector(".payment-brands__item img, .cards-brand-icons img");


    public PaymentServicesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    public void open() {
        driver.get("https://mts.by");
    }

    public void acceptCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cookieBtn)).click();
        } catch (Exception ignored) {
        }
    }

    public void selectTab(String tabName) {
        wait.until(ExpectedConditions.elementToBeClickable(selectHeader)).click();
        By tabLocator = By.xpath("//ul[@class='select__list']//p[contains(text(),'" + tabName + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(tabLocator)).click();
    }
    public String getPlaceholderTextByLabel(String partOfPlaceholder) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("input")));
        List<WebElement> inputs = driver.findElements(By.tagName("input"));

        for (WebElement input : inputs) {
            String placeholder = input.getAttribute("placeholder");
            if (input.isDisplayed() && placeholder != null &&
                    placeholder.toLowerCase().contains(partOfPlaceholder.toLowerCase())) {
                return placeholder;
            }
        }
        throw new RuntimeException("Не удалось найти видимое поле с плейсхолдером: " + partOfPlaceholder);
    }

    @Step("Заполнение платежных реквизитов: номер/счет {account}, сумма {sum}, email {email}")
    public void fillPaymentDetails(String account, String sum, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(activeAccountInput)).sendKeys(account);
        driver.findElement(activeSumInput).sendKeys(sum);
        driver.findElement(activeEmailInput).sendKeys(email);
    }
    @Step("Клик на 'Продолжить'")
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    @Step("Ожидание, переключение в первый фрейм системы оплаты")
    public void switchToPaymentFrame() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@src, 'bepaid')]")));
    }
    @Step("Получение отображаемой суммы к оплате")
    public String getPaymentAmountText() {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pay-description__cost")));
        wait.until(d -> !el.getText().trim().isEmpty());
        return el.getText();
    }
    @Step("Получение информации о платеже (номер телефона)")
    public String getPaymentInfoText() {
        By locator = By.cssSelector(".pay-description__text");
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(d -> !el.getText().trim().isEmpty());
        return el.getText();
    }
    @Step("Проверка видимости поля: {fieldName}")
    public boolean isFieldVisible(String fieldName) {
        try {
            By universalLocator = By.xpath("//input[contains(@id,'cv') or contains(@placeholder,'CVC') or contains(@name,'cv')]");
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(universalLocator));
            return el != null;
        } catch (Exception e) {
            try {
                return driver.findElement(By.className("cc-cvc")).isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
    }
@Step("Получение текста подписи для поля: '{labelName}")
    public String getCardLabel(String labelName) {
        By locator = By.xpath("//label[contains(text(),'" + labelName + "')]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }
@Step("Проверка иконок платёжных систем")
    public int getPaymentIconsCount() {
        return driver.findElements(By.cssSelector(".payment-brands img, .cards-brand-icons img")).size();
    }
}