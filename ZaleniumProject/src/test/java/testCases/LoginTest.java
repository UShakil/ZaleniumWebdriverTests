package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginTest {

    RemoteWebDriver driver;
    DesiredCapabilities dc;

    @BeforeTest
    @Parameters("browser")
    void setup( String br) throws MalformedURLException {
        dc = new DesiredCapabilities();
        if (br.equals("chrome"))
        {
            dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
            dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        }
        else if (br.equals("firefox"))
        {
            dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
            dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        }

        URL url = new URL ("http://localhost:4444/wd/hub");

        driver = new RemoteWebDriver(url,dc);
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    void loginTest() throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='ico-login']")).click();
        driver.findElement(By.id("Email")).sendKeys("pavanoltraining@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("Test@123");

        driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();

        Thread.sleep(5000);

        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
    }

    @AfterTest
    void tearDown()
    {
        driver.quit();
    }
}
