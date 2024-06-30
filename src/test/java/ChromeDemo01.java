import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.time.Duration;

public class ChromeDemo01 {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Setup completed successfully.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Teardown completed successfully.");
        }
    }

    @Test
    public void testLogoutandprofileButton() {
        // Step 1: Navigate to the login page
        driver.get("https://shop.pragmatic.bg/admin/index.php?route=common/login");

        // Step 2: Enter login credentials
        WebElement username = driver.findElement(By.id("input-username"));
        WebElement password = driver.findElement(By.id("input-password"));
        username.sendKeys("admin10");
        password.sendKeys("parola123!");

        // Step 3: Submit the login form
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Step 4: Verify that you are logged in by checking for the user profile element
        WebElement userProfile = driver.findElement(By.id("user-profile"));
        Assert.assertTrue(userProfile.isDisplayed(), "User profile is not displayed, hence not logged in.");

        // Step 5: Verify the logout button text (or presence)
        List<WebElement> logoutButtons = driver.findElements(By.xpath("//span[contains(@class, 'hidden-xs') and contains(@class, 'hidden-sm') and contains(@class, 'hidden-md')]"));
        Assert.assertFalse(logoutButtons.isEmpty(), "Logout button is not found.");
    }
}