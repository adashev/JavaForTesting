package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        app.getNavigationHelper().gotoAddContactPage();
        app.getContactHelper().createContact(new ContactData("К", "О", "test1", "Онежская, 1", "45-90", "m@ma.ru"),true);
        app.getNavigationHelper().gotoHomePage();
    }

}
