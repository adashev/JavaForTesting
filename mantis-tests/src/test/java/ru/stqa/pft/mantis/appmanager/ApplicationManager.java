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
    private WebDriver wd; // сделали переменную private в 8.4 -3:10
    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp; // 8.5

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties(); // 6.10
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");// 6.10
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));// 6.10
    }

    public void stop()
    {
        if (wd != null){ // сдобавили проверку в 8.4 -2:45
            wd.quit();
        }
    }

    public HttpSession newSession(){ // в 8.3
        return new HttpSession(this);
    } // 8.3. Конструируем новую сессию

    public String getProperty(String key) {
        return properties.getProperty(key); // key - имя свойства, которое нужно извлечь
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) { // в 8.4 -1:35 обеспечивает отсутствие повторонй инициализации
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper; //
    }

    public FtpHelper ftp(){ // 8.5
        if (ftp == null){ // если помощник еще не инициал-ан, то...
            ftp = new FtpHelper(this);
        }
        return ftp; // 8.5  -9:54
    }


    public WebDriver getDriver() { // метод ленивой инициализации браузера.
        // драйвер будет инициализ. в тот момент, когда к нему кто-то обратится
        if (wd == null){ // код ниже перенесли из init() в 8.4 -3:45
            if (browser.equals(BrowserType.FIREFOX)){
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            //при time = 0 неявные ожидания выключены
            wd.get(properties.getProperty("web.baseUrl"));// в 6.10 + создали конфиг. файл local.properties
        }
        return wd;
    }
}
