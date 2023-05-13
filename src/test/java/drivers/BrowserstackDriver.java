package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.MobileConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {
        public static MobileConfig config = ConfigFactory.create(MobileConfig.class,
                System.getProperties());

        @Nonnull
        @Override
        public WebDriver createDriver(@Nonnull Capabilities capabilities) {
            MutableCapabilities mutableCapabilities = new MutableCapabilities();
            mutableCapabilities.merge(capabilities);

            // Set your access credentials
            mutableCapabilities.setCapability("browserstack.user", config.getLogin());
            mutableCapabilities.setCapability("browserstack.key", config.getPassword());

            // Set URL of the application under test
            mutableCapabilities.setCapability("app", config.getAppUrl());

            // Specify device and os_version for testing
            mutableCapabilities.setCapability("device", config.getDevice());
            mutableCapabilities.setCapability("os_version", config.getOsVersion());

            // Set other BrowserStack capabilities
            mutableCapabilities.setCapability("project", config.getProjectName());
            mutableCapabilities.setCapability("build", config.getBuildName());
            mutableCapabilities.setCapability("name", config.getTestName());

            // Initialise the remote Webdriver using BrowserStack remote URL
            // and desired capabilities defined above
            try {
                return new RemoteWebDriver(
                        new URL(config.getRemoteUrl()), mutableCapabilities);
                        //new URL("http://hub.browserstack.com/wd/hub"), mutableCapabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }