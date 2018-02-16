package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {
    protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
    // static добавили в 5.1. app становиться самостоятельной глобальной переменной. Общей для всех объектов и тестов

    @BeforeSuite // вместо @BeforeMethod в 5.1
    public void setUp() throws Exception {
        app.init();
    }

    //@AfterSuite // вместо @BeforeMethod в 5.1
    public void tearDown() {
        app.stop();
    }
}
