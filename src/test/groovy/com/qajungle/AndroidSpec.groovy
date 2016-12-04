package com.qajungle

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import spock.lang.Specification

/**
 * Test for Android devices with Appium
 */
class AndroidSpec extends Specification{

    DesiredCapabilities capabilities
    WebDriver driver

    def setup() {
        Properties env = new Properties()
        File propertiesFile = new File('build/resources/test/device.properties')
        propertiesFile.withInputStream {
            env.load(it)
        }

        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, env.device)
        capabilities.setCapability(MobileCapabilityType.PLATFORM, env.platform);
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, env.browser)
        driver = new AndroidDriver<WebElement>(new URL(env.server), capabilities)
    }

    def cleanup() {
        driver.quit();
    }

    /**
     * Check that we can populate comments field in form
     */
    def "Populate comments field in form"() {
        given: "a testing web page"
        driver.get("http://saucelabs.com/test/guinea-pig");
        when: "a element to test"
        WebElement div = driver.findElement(By.id("i_am_an_id"));
        then: "we have a correct text"
        assert "I am a div" == div.getText() //check the text retrieved matches expected value
        driver.findElement(By.id("comments")).sendKeys("My comment"); //populate the comments field by id.
    }

}
