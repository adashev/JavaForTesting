package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;


public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
            //= new ApplicationManager(BrowserType.CHROME);

    // static добавили в 5.1. app становиться самостоятельной глобальной переменной (общей для всех объектов и тестов)

    @BeforeSuite // вместо @BeforeMethod в 5.1
    public void setUp() throws Exception {
        app.init();
    }

    /*@AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }*/
}
