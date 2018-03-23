package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase { // добавлен в 8.5

    /*private final ApplicationManager app;
    private WebDriver wd;*/ // закоментино в 8.6

    public RegistrationHelper(ApplicationManager app) {
        super(app); // вызываем конструктор базового класса (добавили в 8.6 -23:25)
        //this.app = app;  закоментино в 8.6
        //wd = app.getDriver(); // делали ленивую инициализацию драйвера. акоментино в 8.6
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl")+"/signup_page.php");
        type(By.name("username"),username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']")); // 8.6  -21:10


    }
}

