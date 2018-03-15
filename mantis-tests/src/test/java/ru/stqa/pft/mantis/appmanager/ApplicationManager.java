package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties; // 6.10
    WebDriver wd;

    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties(); // 6.10
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");// 6.10
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));// 6.10

        if (browser.equals(BrowserType.FIREFOX)){
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        //при time = 0 неявные ожидания выключены

        //wd.get("http://localhost/addressbook");
        wd.get(properties.getProperty("web.baseUrl"));// в 6.10 + создали конфиг. файл local.properties
    }

    public void stop() {
        wd.quit();
    }
}
