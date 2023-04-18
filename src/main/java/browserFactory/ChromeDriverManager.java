package browserFactory;

import java.util.Collections;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();

		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches",  Collections.singletonList("enable-automation"));
		options.addArguments("--disable-notifications");
		
		return new ChromeDriver(options);
	}

}
