package com.softwaremathematics.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.configuration.ConfigLoader;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Jsoup;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicWebAutomation  {

    String host;

    private final String email;

    private final String password;

    private final String subject;

    private WebDriver driver;
    public String mainWindowHandle;
    private ExtentReports extent;
    private final ExtentTest test;
    public String actionType;
    public String value;
    public String xpath;
    WebDriverWait wait;

    public DynamicWebAutomation(ExtentReports extent,ExtentTest test, String browser, String email, String password, String subject) throws InterruptedException, IOException {
        this.extent=extent;
        this.test = test;
        openBrowser(browser);
        this.email=email;
        this.password=password;
        this.subject=subject;
    }

    public void openWebsite(String url) {
        try {
            if (driver == null) {
                throw new IllegalStateException("WebDriver was not initialized. Check browser type or configuration.");
            }
            driver.get(url);
            handleAlert();
            logStep(Status.INFO, actionType , "The URL is added in Browser as " + url);
        } catch (Exception e) {
            logStepException(e);
        }
    }

    public void clickElement(String xpath) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            element.click();
            handleAlert();
            logStep(Status.INFO, actionType, "Clicked element: " + xpath);

        } catch (Exception e) {
            logStepException(e);
            System.out.println("Click action failed: " + e.getMessage());
            throw e;
        }
    }

    public void enterText(String xpath, String text) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            element.sendKeys(text);
            handleAlert();
            logStep(Status.INFO, actionType, "Entered text: '" + value + "' into " + xpath);

        } catch (Exception e) {
            logStepException(e);
            System.out.println("Typing action failed: " + e.getMessage());
            throw e;
        }
    }

    public void scrollToElement(String xpath) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = driver.findElement(By.xpath(xpath));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Scroll until the element is fully visible
            while (!isElementInViewport(element)) {
                js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
                Thread.sleep(500); // Small delay to allow the scroll action to complete
            }

            wait.until(ExpectedConditions.elementToBeClickable(element));

            // Click using JavaScript in case WebDriver fails
            try {
                element.click();
                logStep(Status.INFO, actionType, "Scrolled to element: " + xpath);
            } catch (Exception e) {
                logStepException(e);
                js.executeScript("arguments[0].click();", element);
            }

            handleAlert();
        } catch (Exception e) {
            System.out.println("Scrolling action failed: " + e.getMessage());
            throw e;
        }
    }

    private boolean isElementInViewport(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript(
                "var rect = arguments[0].getBoundingClientRect(); " +
                        "return (rect.top >= 0 && rect.left >= 0 && rect.bottom <= window.innerHeight && rect.right <= window.innerWidth);",
                element
        );
    }

    public void handleAlert() {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.alertIsPresent());

            Alert alert = driver.switchTo().alert();
            System.out.println("Alert Found: " + alert.getText());
            alert.accept();
        } catch (NoAlertPresentException | TimeoutException e) {
            System.out.println("No alert present.");
        }
    }

    public boolean validate(String xpath, String expectedValue) {

        System.out.println("Start" + new Date());
        try {
            Thread.sleep(5000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        handleAlert();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        String actualValue = element.getText();
        System.out.println("End " + actualValue + " " + new Date());
        if (actualValue.equalsIgnoreCase(expectedValue)) {
            return true;
        }
        return false;

    }

    public void switchWindow(String xpath, String email) {
        WebDriver newDriver = new ChromeDriver();

        String mainHandle = newDriver.getWindowHandle();
        Set<String> allHandles = newDriver.getWindowHandles();
        for (String handle : allHandles) {
            if (!handle.equals(mainHandle)) {
                newDriver.switchTo().window(handle);
                newDriver.manage().window().maximize();
                break;
            }
        }

        newDriver.findElement(By.xpath(xpath)).sendKeys(email);
    }

    public void getEmail(String serverDomain, String emailXpath){
        String emailId = "user" + System.currentTimeMillis() + "@" + serverDomain;
        driver.findElement(By.xpath(emailXpath)).sendKeys(emailId);

    }
    public void closeBrowser() {
        try {
            driver.quit();
        } catch (Exception e) {
            logStepException(e);
            throw e;
        }

    }

    public void enterOtpAndVerify(String xpath) throws Exception {
        String otp = getOtpFromEmail();
        System.out.println("Fetched OTP: " + otp);
        if (driver != null) {

            driver.findElement(By.xpath(xpath)).sendKeys(otp);

        } else {
            System.out.println("Browser not initialized!");
        }
    }

    public String getOtpFromEmail() throws Exception {
        String host;

        if (email.endsWith("@gmail.com")) {
            host = "imap.gmail.com";
        } else if (email.endsWith("@outlook.com") || email.endsWith("@hotmail.com") || email.endsWith("@live.com")) {
            host = "outlook.office365.com";
        } else {
            throw new IllegalArgumentException("Unsupported email provider: " + email);
        }

        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");

        Session session = Session.getInstance(props);
        Store store = null;
        Folder inbox = null;

        try {
            store = session.getStore("imaps");
            store.connect(host, email, password);
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            long startTime = System.currentTimeMillis();
            long timeout = 60000L; // wait up to 60 seconds
            long pollInterval = 5000L;

            while (System.currentTimeMillis() - startTime < timeout) {
                Message[] messages = inbox.getMessages();
                Date now = new Date();

                for (int i = messages.length - 1; i >= 0; i--) {
                    MimeMessage msg = (MimeMessage) messages[i];
                    Date receivedDate = msg.getReceivedDate();
                    if (receivedDate == null) continue;

                    long ageMillis = now.getTime() - receivedDate.getTime();
                    if (ageMillis > 60000) continue; // only process emails from the last 60 seconds

                    String emailSubject = msg.getSubject();
                    if (emailSubject != null && emailSubject.contains(subject)) {
                        String body = getTextFromMessage(msg);
                        Matcher matcher = Pattern.compile("\\b\\d{6}\\b").matcher(body);
                        if (matcher.find()) {
                            return matcher.group(); // ✅ Found OTP
                        }
                    }
                }

                Thread.sleep(pollInterval); // Wait before next check
            }

            throw new RuntimeException("OTP email not received within timeout.");

        } catch (NoSuchProviderException e) {
            throw new RuntimeException("No Such Provider Exception: " + e.getMessage());
        } catch (MessagingException e) {
            throw new RuntimeException("Messaging Exception: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("IO Exception: " + e.getMessage());
        } finally {
            if (inbox != null && inbox.isOpen()) {
                inbox.close(false);
            }
            if (store != null) {
                store.close();
            }
        }
    }


    private static String getTextFromMessage(Message message) throws Exception {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("text/html")) {
            return message.getContent().toString(); // or parse HTML if needed
        } else if (message.isMimeType("multipart/*")) {
            return getTextFromMimeMultipart((MimeMultipart) message.getContent());
        }
        return "";
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();

        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);

            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent().toString());
            } else if (bodyPart.isMimeType("text/html")) {
                // If text/plain not found, fallback to HTML
                result.append(Jsoup.parse(bodyPart.getContent().toString()).text());
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }
        return result.toString();
    }


    public void executeAutomation(List<Map<String, String>> actionSequence, String featureName) throws InterruptedException {

        for (Map<String, String> action : actionSequence) {
            actionType = action.get("action");
            xpath = action.get("xpath");
            value = action.get("value");

            try {
                switch (actionType.toLowerCase()) {
                    case "open":
                        openWebsite(value);
                        break;
                    case "click":
                        clickElement(xpath);
                        break;
                    case "text":
                        enterText(xpath, value);
                        break;
                    case "scroll":
                        scrollToElement(xpath);
                        break;
                    case "otp":
                        enterOtpAndVerify(xpath);
                        break;
                    case "window":
                        String mainWindowHandle = driver.getWindowHandle();
                        switchToPopupWindow(driver, mainWindowHandle, 10);
                        break;
                    case "main":
                        switchToMain();
                        break;
                    case "brokenlinks":
                        validateBrokenLinks(xpath);
                        break;
                    case "validate":
                        if (!validate(xpath, value)) {
                            logStep(Status.FAIL, actionType, "Validation failed! Expected: " + value +
                                    " | Found: " + driver.findElement(By.xpath(xpath)).getText());
                        } else {
                            logStep(Status.PASS, actionType, "Validation Passed! Expected: " + value +
                                    " | Found: " + driver.findElement(By.xpath(xpath)).getText());
                        }
                        break;
                    default:
                        logStep(Status.WARNING, actionType, "Unknown action: " + actionType);
                }
                System.out.println("Step Executed: " + actionType + " | Locator: " + xpath + " | Value: " + value);
            } catch (NoSuchElementException | InterruptedException ex) {
                logStep(Status.FAIL,  actionType , " | Locator: " + xpath + " | Value: " + value);
                ex.printStackTrace();
                throw ex;
            } catch (Exception e) {
                logStepException(e);
            }

        }
    }


    public void validateBrokenLinks(String xpath) throws InterruptedException {
        Thread.sleep(5000L);
        List<WebElement> anchorTags = driver.findElements(By.tagName(xpath));
        Set<String> links = new HashSet<>();

        for (WebElement anchor : anchorTags) {
            String href = anchor.getAttribute("href");
            if (href != null && href.startsWith("http")) {
                links.add(href);
            }
        }

        // 🔍 Check each link
        for (String link : links) {
            checkLink(link);
        }
    }

    private void checkLink(String urlStr) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(urlStr).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.connect();

            int statusCode = connection.getResponseCode();
            if (statusCode >= 400) {
                logStep(Status.FAIL, actionType, "Broken link: " + urlStr + " | Status: " + statusCode);
            } else {
                logStep(Status.PASS, actionType, "Working link: " + urlStr + " | Status: " + statusCode);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error: " + urlStr + " | " + e.getMessage());
        }
    }

    private void logStep(Status status, String actionType, String message) {
        test.log(status, "**Action:** " + actionType + "<br>**Details:** " + message);
        System.out.println(status + ": " + message);
    }

    public void logStepException(Exception e) {
        if (driver != null) {
            test.log(Status.FAIL, "Exception occurred: " + e.getMessage());
        } else {
            logStep(Status.FAIL, null,"Driver is not initialized");
        }
    }



    private void setupExtentReports() {

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(new File("UITestReport.html"));
        htmlReporter.config().setDocumentTitle("UI Test Report");
        htmlReporter.config().setReportName("UI Message Processing");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Environment", "Local");
        extent.setSystemInfo("Tester", "Automation Team");

    }


    public void openBrowser(String browserType) throws InterruptedException, IOException {

        try {
            File extensions;
            Properties properties = new Properties();
            InputStream input = ConfigLoader.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties");

            if (input == null) {
                throw new RuntimeException("application.properties not found in classpath!");
            }

            properties.load(input);
            extensions = new File(properties.getProperty("browser.extension.path"));
            if ("chrome".equalsIgnoreCase(browserType)) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--no-sandbox"); // Disable sandboxing
                options.addArguments("--disable-dev-shm-usage"); // Avoid /dev/shm issues
                options.addArguments("--disable-gpu");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-notifications");
                options.addArguments("--no-default-browser-check");
                options.addArguments("--disable-save-password-bubble");
                options.addArguments("--disable-password-generation");
                options.addArguments("--disable-infobars");
                options.addArguments("--start-maximized");
                options.addArguments("--window-size=1920,1080");
                String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                        + "AppleWebKit/537.36 (KHTML, like Gecko) "
                        + "Chrome/125.0.6422.112 Safari/537.36";

                options.addArguments("--user-agent=" + userAgent);
                options.addExtensions(extensions);
                driver = new ChromeDriver(options);

            } else if ("firefox".equalsIgnoreCase(browserType)) {
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless"); // For headless mode
                options.addPreference("general.useragent.override",
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                                "AppleWebKit/537.36 (KHTML, like Gecko) " +
                                "Chrome/135.0.7049.96 Safari/537.36");
                options.addPreference("dom.webnotifications.enabled", false); // Disable notifications
                options.addPreference("dom.disable_open_during_load", true); // Disable popups
                options.addPreference("signon.rememberSignons", false); // Disable password saving
                options.addPreference("browser.privatebrowsing.autostart", true); // Private mode
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");

                driver = new FirefoxDriver(options);
                Thread.sleep(5000);

                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                    if (!driver.getCurrentUrl().contains("adblockultimate.net")) {
                        driver.close();
                    }
                }
            } else if ("edge".equalsIgnoreCase(browserType)) {
                WebDriverManager.edgedriver().setup();

                EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-notifications");
                options.addArguments("--no-default-browser-check");
                options.addArguments("--disable-save-password-bubble");
                options.addArguments("--disable-password-generation");
                options.addArguments("--disable-infobars");
                options.addArguments("--start-maximized");
                options.addArguments("--window-size=1920,1080");

                String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/135.0.7049.96 Safari/537.36";
                options.addArguments("--user-agent=" + userAgent);

            options.addExtensions(extensions);

                driver = new EdgeDriver(options);
                    Thread.sleep(5000);

                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                    if (!driver.getCurrentUrl().contains("adblockultimate.net")) {
                        driver.close();
                    }
                }
            }
            if (driver != null) {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().window().maximize();
                Thread.sleep(5000); // Optional: wait for browser stabilization

                // Keep only the main non-adblock window open
                String mainWindow = driver.getWindowHandle();
                Set<String> allWindows = driver.getWindowHandles();

                for (String handle : allWindows) {
                    driver.switchTo().window(handle);
                    String currentUrl = driver.getCurrentUrl();
                    if (currentUrl.contains("adblockultimate.net")) {
                        driver.close();  // Close adblock window
                    }
                }

                // Switch back to main window
                driver.switchTo().window(mainWindow);

            } else {
                logStep(Status.FAIL, "DriverCheck", "Driver is null. Automation cannot proceed.");
            }




        } catch (Exception e) {
            logStepException(e);
            throw e;
        }

    }

    public void switchToPopupWindow(WebDriver driver, String mainWindowHandle, long timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(d -> d.getWindowHandles().size() > 1);

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                driver.manage().window().maximize();
                return;
            }
        }
        throw new RuntimeException("Popup window did not appear");
    }

    private void switchToMain() {
        driver.switchTo().window(mainWindowHandle);
    }
}
