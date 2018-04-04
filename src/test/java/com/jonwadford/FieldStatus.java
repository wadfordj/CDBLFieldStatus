package com.jonwadford;

import com.jonwadford.utils.WebTableUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FieldStatus {

  private WebDriver driver;

  @Before
  public void setUp() {
    // Headless ChromeDriver
    System.setProperty(
        "webdriver.chrome.driver", System.getProperty("user.dir") + "/vendor/chromedriver");
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--headless");
    driver = new ChromeDriver(chromeOptions);
  }

  @Test
  public void getFieldStatusTest() {
    driver.get("http://cdbaseball.org/");
    Assert.assertEquals("Central District Baseball League", driver.getTitle());

    WebTableUtils tableBody =
        new WebTableUtils(
            driver.findElement(
                By.cssSelector(
                    "table tbody tr td.RightColWidget  table.FieldStatus_container tbody")));
    // System.out.println(tableBody.getRowCount());

    for (int i = 1; i <= tableBody.getRowCount(); i++) {
      WebTableUtils tableRow =
          new WebTableUtils(
              driver.findElement(
                  By.cssSelector(
                      "table tbody tr td.RightColWidget table.FieldStatus_container tbody")));
      System.out.println(
          "Field and Location: "
              + tableRow.getCellData(i, 0)
              + " - Field Status / Date of Status: "
              + tableRow.getCellData(i, 1));
    }
  }

  @After
  public void tearDown() {
    driver.quit();
  }
}
