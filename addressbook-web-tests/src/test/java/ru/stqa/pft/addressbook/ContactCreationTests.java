package ru.stqa.pft.addressbook;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        gotoAddContactPage();
        fillContactForm(new ContactData("Клим", "Оушен", "Klim75", "Москва, Онежская, 10, кв.44", "+7 (904) 456 77 90", "mail@mail.com"));
        submitContactCreation();
    }

}
