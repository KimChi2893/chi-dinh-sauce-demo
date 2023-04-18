package browserFactory;

import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;
import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		
		return new EdgeDriver(options);
	}	
}
