package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        app.getNavigationHelper().gotoAddContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Клим", "Оушен", "Klim75", "Москва, Онежская, 10, кв.44", "+7 (904) 456 77 90", "mail@mail.com"));
        app.getContactHelper().submitContactCreation();
    }

}
