package main.java;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.join;

public class InvalidInputTest {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://46.149.26.108:4567/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testInvalidInput() throws Exception {
		selenium.refresh();
		selenium.clickAt("//tr[2]/td[2]", "");
		selenium.clickAt("//tr[2]/td[2]", "");
		assertEquals("Invalid move.", selenium.getText("id=output"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
