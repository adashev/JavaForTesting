package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Олег(ред)", "Ага(ред)", "O-1", "Москва, Онежская, 1", "+7 (800) 555 88 99", "mail@gmail.com"));
    app.getContactHelper().updateContactCreation();
  }
}
