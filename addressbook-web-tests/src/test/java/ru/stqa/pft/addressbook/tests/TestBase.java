package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class); // 6.11

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
            //= new ApplicationManager(BrowserType.CHROME);

    // static добавили в 5.1. app становиться самостоятельной глобальной переменной (общей для всех объектов и тестов)

    @BeforeSuite // вместо @BeforeMethod в 5.1
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object [] p){ // в Object [] p - параметры тестового метода
        logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p)); // 6.11
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m, Object [] p){
        logger.info("Stop test " + m.getName() + " with parameters " + Arrays.asList(p)); // 6.11
    }
}
