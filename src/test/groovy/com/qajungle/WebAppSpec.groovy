package com.qajungle

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import spock.lang.Specification

/**
 * Test for Soucelabs testing webapp
 */
class WebAppSpec extends Specification{

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
     * Check that we can access to ticketbis engineering link
     */
    def "Check if the qajungle ticketbis link is correct"() {
        given: "a qajungle blog web page"
        driver.get("http://qajungle.com")
        when: "a link element to test"
        WebElement link = driver.findElement(By.cssSelector('#menu-item-26 > a:nth-child(1)'))
        then: "we have a correct text link"
        assert "Ticketbis Engineering" == link.getText()
    }

}
