package id.ac.ui.cs.advprog.eshop.functional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class HomePageFunctionalTest {
    @LocalServerPort
    private int port;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, port);
    }

    // Simulate create product
    @Test
    void testCreateProduct(ChromeDriver driver) {
        driver.get(baseUrl + "/product/create");

        driver.findElement(By.id("nameInput")).sendKeys("Test Product");
        driver.findElement(By.id("quantityInput")).sendKeys("10");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String currentUrl = driver.getCurrentUrl();
        assertEquals(baseUrl + "/product/list", currentUrl);
    }

    @Test
    void testCreateMultipleProducts(ChromeDriver driver) {
        driver.get(baseUrl + "/product/create");

        // Create first product
        driver.findElement(By.id("nameInput")).sendKeys("Product One");
        driver.findElement(By.id("quantityInput")).sendKeys("5");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Create second product
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys("Product Two");
        driver.findElement(By.id("quantityInput")).sendKeys("15");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Verify redirection to product list
        String currentUrl = driver.getCurrentUrl();
        assertEquals(baseUrl + "/product/list", currentUrl);

        // Verify products in the list
        assertEquals("Product One", driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText());
        assertEquals("5", driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText());
        assertEquals("Product Two", driver.findElement(By.xpath("//tbody/tr[2]/td[1]")).getText());
        assertEquals("15", driver.findElement(By.xpath("//tbody/tr[2]/td[2]")).getText());
    }
}
